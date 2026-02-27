package spring.rag.ai.spring_rag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {
    private String jobRole;
    private String jobDescription;
    private String skills;
    private String companyInfo;
}
