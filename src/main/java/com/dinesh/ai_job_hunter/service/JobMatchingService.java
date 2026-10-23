package com.dinesh.ai_job_hunter.service;

import com.dinesh.ai_job_hunter.dto.JobMatchResponse;
import com.dinesh.ai_job_hunter.entity.ExternalJob;
import com.dinesh.ai_job_hunter.repository.ExternalJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobMatchingService {

    private final ExternalJobRepository externalJobRepository;

    private static final Map<String, Integer> KEYWORDS = Map.of(
            "java", 40,
            "spring boot", 30,
            "backend", 25,
            "rest api", 15,
            "software engineer", 15,
            "software developer", 15,
            "programmer", 15,
            "developer", 10
    );

    public JobMatchingService(
            ExternalJobRepository externalJobRepository) {
        this.externalJobRepository = externalJobRepository;
    }

    public List<JobMatchResponse> findMatchingJobs() {

        return externalJobRepository.findAll()
                .stream()
                .map(job -> new JobMatchResponse(
                        job.getId(),
                        job.getTitle(),
                        job.getCompanyName(),
                        job.getApplicationLink(),
                        calculateMatchScore(job)
                ))
                .filter(match -> match.matchScore() >= 30)
                .sorted(
                        java.util.Comparator.comparingInt(
                                JobMatchResponse::matchScore
                        ).reversed()
                )
                .toList();
    }

    public List<JobMatchResponse> getAllJobScores() {
        return externalJobRepository.findAll()
                .stream()
                .map(job -> new JobMatchResponse(
                        job.getId(),
                        job.getTitle(),
                        job.getCompanyName(),
                        job.getApplicationLink(),
                        calculateMatchScore(job)
                ))
                .sorted(
                        java.util.Comparator.comparingInt(
                                JobMatchResponse::matchScore
                        ).reversed()
                )
                .toList();
    }


    public int calculateMatchScore(ExternalJob job) {

        String title = job.getTitle() == null
                ? ""
                : job.getTitle().toLowerCase();

        String description = job.getDescription() == null
                ? ""
                : job.getDescription().toLowerCase();

        int score = 0;

        if (title.contains("java")) {
            score += 30;
        }

        if (title.contains("backend")) {
            score += 20;
        }

        if (title.contains("software engineer")) {
            score += 20;
        }

        if (description.contains("java")) {
            score += 15;
        }

        if (description.contains("spring boot")) {
            score += 15;
        }

        if (description.contains("rest api")) {
            score += 10;
        }

        if (description.contains("sql")) {
            score += 10;
        }

        return Math.min(score, 100);
    }


}

