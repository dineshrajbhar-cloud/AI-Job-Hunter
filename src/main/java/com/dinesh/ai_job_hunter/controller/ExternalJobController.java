package com.dinesh.ai_job_hunter.controller;

import com.dinesh.ai_job_hunter.entity.ExternalJob;
import com.dinesh.ai_job_hunter.service.ExternalJobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/external-jobs")
public class ExternalJobController {

    private final ExternalJobService externalJobService;

    public ExternalJobController(
            ExternalJobService externalJobService) {
        this.externalJobService = externalJobService;
    }

    @GetMapping
    public List<ExternalJob> getAllJobs() {
        return externalJobService.findAllJobs();
    }

    @GetMapping("/search")
    public List<ExternalJob> searchJobs(
            @RequestParam String title) {

        return externalJobService.searchJobsByTitle(title);
    }
}
