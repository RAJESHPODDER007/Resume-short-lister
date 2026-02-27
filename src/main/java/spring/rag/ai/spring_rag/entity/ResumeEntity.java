package spring.rag.ai.spring_rag.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.UUID;
import java.util.Vector;

@Entity
@Table(name = "resume_vector")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data
@Builder
public class ResumeEntity {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;
    @Column(name = "emailId", unique = true)
    private String emailId;
    @Column(name = "content")
    private String content;
    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "metadata", columnDefinition = "jsonb")
    private String metadata;
    @Column(name = "embedding")
    @JdbcTypeCode(SqlTypes.VECTOR)
    @Array(length = 768)
    private float[] embedding;
    @Column(name = "source")
    private String skills;
}
