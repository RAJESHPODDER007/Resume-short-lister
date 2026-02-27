package spring.rag.ai.spring_rag.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.stereotype.Service;
import spring.rag.ai.spring_rag.dto.JobRequest;
import spring.rag.ai.spring_rag.entity.JobEntity;
import spring.rag.ai.spring_rag.repository.JobRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobPgVectorStoreService implements VectorStore {

    private final EmbeddingModel embeddingModel;
    private final JobRepository jobRepository;

    public float[] embed(String content){
        return this.embeddingModel.embed(content);
    }

    @Override
    public void add(List<Document> documents) {
        documents.stream().forEach(doc-> {
            ObjectMapper mapper = new ObjectMapper();
            try {
               var jobRequest =  mapper.readValue(doc.getText(), JobRequest.class);
                var embeddings = this.embed(jobRequest.getSkills());

                try {
                    jobRepository.save(JobEntity.builder()
                            .jobId(UUID.randomUUID().toString().substring(0,8))
                            .companyInfo(jobRequest.getCompanyInfo())
                            .content(doc.getText())
                            .metadata(new ObjectMapper().writeValueAsString(jobRequest.getSkills()))
                            .embedding(embeddings)
                            .jobRole(jobRequest.getJobRole())
                            .jobDescription(jobRequest.getJobDescription())
                            .skills(jobRequest.getSkills())
                            .build());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void delete(List<String> idList) {
    }

    @Override
    public void delete(Filter.Expression filterExpression) {
    }

    @Override
    public List<Document> similaritySearch(SearchRequest request) {
        float[] queryEmbedding = embeddingModel.embed(request.getQuery());
        var result = jobRepository.findBySimilaritySearch(toPgVector(queryEmbedding),request.getTopK());
        result.stream().forEach(e->System.out.println(e.getDistance()));
        return result.stream()
                 .map(e-> e.getJobId())
                 .map(content-> new Document(content))
                 .collect(Collectors.toList());
    }

    private String toPgVector(float[] vector) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < vector.length; i++) {
            sb.append(vector[i]);
            if (i < vector.length - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
