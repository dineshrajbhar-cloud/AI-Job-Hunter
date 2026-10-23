package com.dinesh.ai_job_hunter.controller;

import com.dinesh.ai_job_hunter.dto.JobMatchResponse;
import com.dinesh.ai_job_hunter.entity.ExternalJob;
import com.dinesh.ai_job_hunter.service.JobMatchingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matching")
public class JobMatchingController {

    private final JobMatchingService jobMatchingService;

    public JobMatchingController(
            JobMatchingService jobMatchingService) {
        this.jobMatchingService = jobMatchingService;
    }

    @GetMapping("/jobs")
    public List<JobMatchResponse> getMatchingJobs() {
        return jobMatchingService.findMatchingJobs();
    }

    @GetMapping("/all")
    public List<JobMatchResponse> getAllJobScores() {
        return jobMatchingService.getAllJobScores();
    }
}


