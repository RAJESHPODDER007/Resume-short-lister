package spring.rag.ai.spring_rag.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.rag.ai.spring_rag.dto.JobRequest;
import spring.rag.ai.spring_rag.service.JobService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping(value = "/job")
    public String postJob(@RequestBody JobRequest jobRequest){
        System.out.println(jobRequest);
        return jobService.addJob(jobRequest);
    }
}
