package com.dinesh.ai_job_hunter.dto;

import java.util.List;

public record ExternalJobMatchResponse(
        List<MatchedJob> matchedJobs
) {
    public record MatchedJob(
            Long jobId,
            String title,
            String company,
            String location,
            double salary,
            int matchPercentage,
            String reason
    ) {
    }
}

