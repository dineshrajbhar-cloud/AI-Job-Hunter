package com.dinesh.ai_job_hunter.service;

import com.dinesh.ai_job_hunter.client.AiMatchingClient;
import com.dinesh.ai_job_hunter.dto.ExternalJobMatchRequest;
import com.dinesh.ai_job_hunter.dto.ExternalJobMatchResponse;
import com.dinesh.ai_job_hunter.entity.ExternalJob;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeMatchingService {

    private final ExternalJobService externalJobService;
    private final AiMatchingClient aiMatchingClient;

    public ResumeMatchingService(
            ExternalJobService externalJobService,
            AiMatchingClient aiMatchingClient) {
        this.externalJobService = externalJobService;
        this.aiMatchingClient = aiMatchingClient;
    }

    public ExternalJobMatchResponse matchResume(String resumeText) {

        List<ExternalJob> savedJobs =
                externalJobService.findAllJobs();

        if (savedJobs.isEmpty()) {
            throw new IllegalStateException(
                    "No external jobs found. Discover jobs first."
            );
        }

        List<ExternalJobMatchRequest.ExternalJobInput> jobs =
                savedJobs.stream()
                        .map(job ->
                                new ExternalJobMatchRequest.ExternalJobInput(
                                        job.getId(),
                                        job.getTitle(),
                                        job.getCompanyName() == null
                                                ? "Unknown"
                                                : job.getCompanyName(),
                                        "Not specified",
                                        0.0,
                                        job.getDescription() == null
                                                ? ""
                                                : job.getDescription()
                                ))
                        .toList();

        ExternalJobMatchRequest request =
                new ExternalJobMatchRequest(resumeText, jobs);

        return aiMatchingClient.matchExternalJobs(request);
    }
}
