package spring.rag.ai.spring_rag.docloader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.stereotype.Service;
import spring.rag.ai.spring_rag.entity.ResumeEntity;
import spring.rag.ai.spring_rag.repository.ResumeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumePgVectorStoreService implements VectorStore {

    private final EmbeddingModel embeddingModel;
    private final ResumeRepository resumeRepository;

    public float[] embed(String content){
        return this.embeddingModel.embed(content);
    }

    @Override
    public void add(List<Document> documents) {
/*            documents.stream().forEach(doc-> {
                var embeddings = this.embed(doc.getText());
                try {
                    *//*resumeRepository.save(ResumeEntity.builder()
                                    .content(doc.getText())
                                    .metadata(new ObjectMapper().writeValueAsString(doc.getMetadata()))
                                    .embedding(embeddings)
                                    .source(doc.getMetadata().get("source").toString())
                                    .author(doc.getMetadata().get("author").toString())
                            .build());*//*
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });*/
    }

    @Override
    public void delete(List<String> idList) {

    }

    @Override
    public void delete(Filter.Expression filterExpression) {

    }

    public List<Document> similaritySearch(SearchRequest request) {
        return null;
    }

    @Override
    public List<Document> similaritySearch(String query) {
        throw new UnsupportedOperationException("Implement if needed");
    }
}