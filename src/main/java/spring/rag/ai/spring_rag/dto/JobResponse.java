package spring.rag.ai.spring_rag.dto;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class JobResponse {
    private String jobId;
    private double distance;
}
