package com.dinesh.ai_job_hunter.service;

import com.dinesh.ai_job_hunter.dto.HimalayasJobDto;
import org.springframework.stereotype.Component;

@Component
public class JobNormalizer {

    public String normalizeGuid(HimalayasJobDto job) {
        if (job.guid() == null || job.guid().isBlank()) {
            throw new IllegalArgumentException(
                    "Job GUID is required for deduplication"
            );
        }

        return job.guid().trim();
    }
}