package com.dinesh.ai_job_hunter.dto;

public record JobMatchResponse(
        Long jobId,
        String title,
        String companyName,
        String applicationLink,
        int matchScore
) {
}


