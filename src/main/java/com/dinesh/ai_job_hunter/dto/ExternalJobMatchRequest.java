package com.dinesh.ai_job_hunter.dto;

import java.util.List;

public record ExternalJobMatchRequest(
        String resumeText,
        List<ExternalJobInput> jobs
) {
    public record ExternalJobInput(
            Long jobId,
            String title,
            String company,
            String location,
            double salary,
            String description
    ) {
    }
}
