package com.dinesh.ai_job_hunter.repository;

import com.dinesh.ai_job_hunter.entity.ExternalJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalJobRepository
        extends JpaRepository<ExternalJob, Long> {

    Optional<ExternalJob> findByGuid(String guid);

    boolean existsByGuid(String guid);
}