package spring.rag.ai.spring_rag.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.rag.ai.spring_rag.dto.ResumeExtractedResponseDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ResumeService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/rag-resume-prompt.st")
    private Resource ragPromptTemplate;

    private final JobPgVectorStoreService jobVector;

    public ResumeService(ChatClient.Builder chatClientBuilder,
                         JobPgVectorStoreService jobVector) {
        this.chatClient = chatClientBuilder
                .build();
        this.jobVector = jobVector;
    }

    public List<Document> readFile(MultipartFile file){
        StringBuilder sb = new StringBuilder();
        try {
            Resource resource = multipartToResource(file);
            PagePdfDocumentReader reader = new PagePdfDocumentReader(resource);
            List<Document> documents = reader.get();
            for (Document doc: documents){
                sb.append(doc.getText());
            }
        }catch (IOException e){
        }
        String information = sb.toString();
        String message = "You extract structured data";
        var result = retrieveAndGenerate(message, information);

        return getJobs(SearchRequest.builder()
                .query(result.getSkills().toString())
                .topK(5)
                .build());
    }

    private Resource multipartToResource(MultipartFile file) throws IOException {
        return new InputStreamResource(file.getInputStream()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
    }

   private ResumeExtractedResponseDto retrieveAndGenerate(String message, String information) {
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(ragPromptTemplate);
       Prompt prompt = new Prompt(List.of(
               systemPromptTemplate.createMessage(
                       Map.of("message", message)),
               new UserMessage(information)));
        ObjectMapper mapper = new ObjectMapper();
        ResumeExtractedResponseDto result = null;
        var aiResponse= chatClient.prompt(prompt).call().content();
        try {
            result = mapper.readValue(aiResponse, ResumeExtractedResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private List<Document> getJobs(SearchRequest request){
        return jobVector.similaritySearch(request);
    }
}
