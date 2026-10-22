package com.dinesh.ai_job_hunter.service;

import com.dinesh.ai_job_hunter.dto.HimalayasJobDto;
import com.dinesh.ai_job_hunter.entity.ExternalJob;
import com.dinesh.ai_job_hunter.repository.ExternalJobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class ExternalJobService {

    private final ExternalJobRepository externalJobRepository;
    private final JobNormalizer jobNormalizer;

    public ExternalJobService(
            ExternalJobRepository externalJobRepository,
            JobNormalizer jobNormalizer) {
        this.externalJobRepository = externalJobRepository;
        this.jobNormalizer = jobNormalizer;
    }

    @Transactional
    public boolean saveJob(HimalayasJobDto job) {

        String guid = jobNormalizer.normalizeGuid(job);

        if (externalJobRepository.existsByGuid(guid)) {
            return false;
        }

        Instant publishedAt = job.pubDate() == null
                ? null
                : Instant.ofEpochSecond(job.pubDate());

        ExternalJob externalJob = new ExternalJob(
                guid,
                job.title(),
                job.companyName(),
                job.applicationLink(),
                publishedAt
        );

        externalJobRepository.save(externalJob);

        return true;
    }
    public List<ExternalJob> findAllJobs() {
        return externalJobRepository.findAll();
    }
}