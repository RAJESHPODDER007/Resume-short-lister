package spring.rag.ai.spring_rag.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;
import spring.rag.ai.spring_rag.dto.JobRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobPgVectorStoreService jobPgVectorStoreService;

    public String addJob(JobRequest jobRequest){
        try {
            ObjectMapper mapper = new ObjectMapper();
            var json = mapper.writeValueAsString(jobRequest);
            Document doc = new Document(json);
            jobPgVectorStoreService.add(List.of(doc));
        }catch (JsonProcessingException e){
            return "Posting failed";
        }
        return "Successfully posted";
    }
}
