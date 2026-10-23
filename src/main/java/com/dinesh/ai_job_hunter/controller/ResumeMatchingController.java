package com.dinesh.ai_job_hunter.controller;

import com.dinesh.ai_job_hunter.dto.ExternalJobMatchResponse;
import com.dinesh.ai_job_hunter.service.ResumeMatchingService;
import com.dinesh.ai_job_hunter.service.ResumeTextExtractor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/matching")
public class ResumeMatchingController {

    private final ResumeTextExtractor resumeTextExtractor;
    private final ResumeMatchingService resumeMatchingService;

    public ResumeMatchingController(
            ResumeTextExtractor resumeTextExtractor,
            ResumeMatchingService resumeMatchingService) {
        this.resumeTextExtractor = resumeTextExtractor;
        this.resumeMatchingService = resumeMatchingService;
    }

    @PostMapping(
            value = "/resume",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ExternalJobMatchResponse matchResume(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        String resumeText =
                resumeTextExtractor.extractText(file);

        return resumeMatchingService.matchResume(resumeText);
    }
}
