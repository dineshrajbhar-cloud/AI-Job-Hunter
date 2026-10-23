package com.dinesh.ai_job_hunter.client;

import com.dinesh.ai_job_hunter.dto.ExternalJobMatchRequest;
import com.dinesh.ai_job_hunter.dto.ExternalJobMatchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

@Component
public class AiMatchingClient {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public AiMatchingClient(
            @Value("${ai.service.base-url}") String baseUrl, ObjectMapper objectMapper) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.objectMapper = objectMapper;
    }

    public ExternalJobMatchResponse matchExternalJobs(
            ExternalJobMatchRequest request) {

        System.out.println("Sending request to Python AI service");
        System.out.println("Jobs count: " + request.jobs().size());

        String jsonBody = objectMapper.writeValueAsString(request);
        byte[] requestBytes = jsonBody.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        System.out.println("JSON body length: " + jsonBody.length());
        System.out.println("Request bytes: " + requestBytes.length);

        return restClient.post()
                .uri("/ai/match-external-jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .contentLength(requestBytes.length)
                .body(requestBytes)
                .retrieve()
                .body(ExternalJobMatchResponse.class);
    }
}



