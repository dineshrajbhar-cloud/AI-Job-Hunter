package com.dinesh.ai_job_hunter.client;

import com.dinesh.ai_job_hunter.dto.HimalayasJobsResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HimalayasJobClient {

    private static final String API_URL =
            "https://himalayas.app/jobs/api";

    private final RestClient restClient;

    public HimalayasJobClient() {
        this.restClient = RestClient.create();
    }

    public HimalayasJobsResponse fetchJobs(int limit) {
        return restClient.get()
                .uri(API_URL + "?limit=" + limit)
                .retrieve()
                .body(HimalayasJobsResponse.class);
    }

    public HimalayasJobsResponse fetchNextPage(
            int limit, String cursor) {

        return restClient.get()
                .uri(API_URL + "?limit=" + limit + "&cursor={cursor}",
                        cursor)
                .retrieve()
                .body(HimalayasJobsResponse.class);
    }
}