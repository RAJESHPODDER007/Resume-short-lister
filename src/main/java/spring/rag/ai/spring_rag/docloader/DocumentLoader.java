package spring.rag.ai.spring_rag.docloader;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentLoader implements CommandLineRunner {

    private final ResumePgVectorStoreService resumePgVectorStoreService;
    @Override
    public void run(String... args) {
        System.out.println("Started");
        List<Document> documents = List.of(
                new Document(
                        "StarlightDB is a serverless graph database designed for real-time " +
                                "analytics on complex, interconnected data."
                        , Map.of("source","pdf","author","Rajesh")
                ),
                new Document(
                        "Hello world discovered by Rajesh"
                        , Map.of("source","pdf","author","Rajesh")
                ));
        //resumePgVectorStoreService.add(documents);
        System.out.println("Documents loaded into VectorStore.");
    }
}