package spring.rag.ai.spring_rag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeExtractedResponseDto {
    private String name;
    private String contactNumber;
    private String yearOfExperience;
    private String email;
    private List<String> skills;
}
