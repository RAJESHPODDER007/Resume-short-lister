package spring.rag.ai.spring_rag.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "job_vector")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class JobEntity {
    @Id
    @Column(name = "jobId")
    private String jobId;
    @Column(name = "jobRole", columnDefinition = "TEXT")
    private String jobRole;
    @Column(name = "jobDescription", columnDefinition = "TEXT")
    private String jobDescription;
    @Column(name = "skills", columnDefinition = "TEXT")
    private String skills;
    @Column(name = "companyInfo", columnDefinition = "TEXT")
    private String companyInfo;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "metadata", columnDefinition = "jsonb")
    private String metadata;
    @Column(name = "embedding")
    @JdbcTypeCode(SqlTypes.VECTOR)
    @Array(length = 768)
    private float[] embedding;
}
