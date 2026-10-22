package com.dinesh.ai_job_hunter.controller;

import com.dinesh.ai_job_hunter.dto.HimalayasJobsResponse;
import com.dinesh.ai_job_hunter.service.JobDiscoveryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discovery")
public class JobDiscoveryController {

    private final JobDiscoveryService jobDiscoveryService;

    public JobDiscoveryController(JobDiscoveryService jobDiscoveryService) {
        this.jobDiscoveryService = jobDiscoveryService;
    }

    @GetMapping("/jobs")
    public HimalayasJobsResponse discoverJobs(
            @RequestParam(defaultValue = "5") int limit) {

        return jobDiscoveryService.discoverJobs(limit);
    }

    @GetMapping("/jobs/next")
    public HimalayasJobsResponse discoverNextPage(
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam String cursor) {

        return jobDiscoveryService.discoverNextPage(limit, cursor);
    }
}