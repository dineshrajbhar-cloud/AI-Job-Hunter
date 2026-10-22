package com.dinesh.ai_job_hunter.service;

import com.dinesh.ai_job_hunter.client.HimalayasJobClient;
import com.dinesh.ai_job_hunter.dto.HimalayasJobDto;
import com.dinesh.ai_job_hunter.dto.HimalayasJobsResponse;
import org.springframework.stereotype.Service;

@Service
public class JobDiscoveryService {

    private final HimalayasJobClient himalayasJobClient;
    private final ExternalJobService externalJobService;

    public JobDiscoveryService(
            HimalayasJobClient himalayasJobClient,
            ExternalJobService externalJobService) {

        this.himalayasJobClient = himalayasJobClient;
        this.externalJobService = externalJobService;
    }

    public HimalayasJobsResponse discoverJobs(int limit) {
        validateLimit(limit);

        HimalayasJobsResponse response =
                himalayasJobClient.fetchJobs(limit);

        saveDiscoveredJobs(response);

        return response;
    }

    public HimalayasJobsResponse discoverNextPage(
            int limit,
            String cursor) {

        validateLimit(limit);

        if (cursor == null || cursor.isBlank()) {
            throw new IllegalArgumentException(
                    "Cursor must not be empty");
        }

        HimalayasJobsResponse response =
                himalayasJobClient.fetchNextPage(limit, cursor);

        saveDiscoveredJobs(response);

        return response;
    }

    private void saveDiscoveredJobs(
            HimalayasJobsResponse response) {

        if (response == null || response.jobs() == null) {
            return;
        }

        for (HimalayasJobDto job : response.jobs()) {

            if (job != null
                    && job.guid() != null
                    && !job.guid().isBlank()
                    && job.title() != null
                    && !job.title().isBlank()) {

                externalJobService.saveJob(job);
            }
        }
    }

    private void validateLimit(int limit) {
        if (limit < 1 || limit > 20) {
            throw new IllegalArgumentException(
                    "Limit must be between 1 and 20");
        }
    }
}
