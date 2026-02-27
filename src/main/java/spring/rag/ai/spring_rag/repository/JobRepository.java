package spring.rag.ai.spring_rag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.rag.ai.spring_rag.dto.JobResponse;
import spring.rag.ai.spring_rag.entity.JobEntity;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, String> {

    @Query(value = """
        SELECT j.job_id as jobId,
        (j.embedding <=> CAST(:queryEmbed AS vector)) AS distance
        FROM job_vector j
        WHERE (j.embedding <=> CAST(:queryEmbed AS vector)) < 0.6
        ORDER BY distance
        LIMIT :limit
        """, nativeQuery = true)
    public List<JobResponse> findBySimilaritySearch(@Param("queryEmbed") String queryEmbedding,
                                                    @Param("limit") int limit);
}
