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

        Instant publishedAt = job.pubDate() == null
                ? null
                : Instant.ofEpochSecond(job.pubDate());

        ExternalJob externalJob = externalJobRepository
                .findByGuid(guid)
                .orElseGet(ExternalJob::new);

        boolean isNewJob = externalJob.getId() == null;

        externalJob.setGuid(guid);
        externalJob.setTitle(job.title());
        externalJob.setCompanyName(job.companyName());
        externalJob.setApplicationLink(job.applicationLink());
        externalJob.setPublishedAt(publishedAt);
        externalJob.setDescription(job.description());

        externalJobRepository.save(externalJob);

        return isNewJob;
    }


    public List<ExternalJob> findAllJobs() {
        return externalJobRepository.findAll();
    }

    public List<ExternalJob> searchJobsByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Job title must not be empty");
        }

        return externalJobRepository
                .findByTitleContainingIgnoreCase(title.trim());
    }
}
