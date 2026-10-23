package com.dinesh.ai_job_hunter.scheduler;

import com.dinesh.ai_job_hunter.service.JobDiscoveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobDiscoveryScheduler {

    private static final Logger log =
            LoggerFactory.getLogger(JobDiscoveryScheduler.class);

    private final JobDiscoveryService jobDiscoveryService;

    public JobDiscoveryScheduler(
            JobDiscoveryService jobDiscoveryService) {
        this.jobDiscoveryService = jobDiscoveryService;
    }

    @Scheduled(fixedDelay = 300000)
    public void discoverJobsAutomatically() {
        try {
            log.info("Starting scheduled job discovery");

            jobDiscoveryService.discoverJobs(5);

            log.info("Scheduled job discovery completed");
        } catch (Exception e) {
            log.error("Scheduled job discovery failed", e);
        }
    }
}