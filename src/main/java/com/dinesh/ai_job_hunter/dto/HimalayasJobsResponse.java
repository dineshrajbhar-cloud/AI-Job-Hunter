package com.dinesh.ai_job_hunter.dto;

import java.util.List;

public record HimalayasJobsResponse(
        String comments,
        Long updatedAt,
        Integer offset,
        Integer limit,
        Long totalCount,
        String nextCursor,
        List<HimalayasJobDto> jobs
) {
}
