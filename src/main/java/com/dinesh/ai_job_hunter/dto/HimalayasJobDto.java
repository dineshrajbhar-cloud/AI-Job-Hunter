package com.dinesh.ai_job_hunter.dto;

import java.math.BigDecimal;
import java.util.List;

public record HimalayasJobDto(
        String title,
        String excerpt,
        String companyName,
        String companySlug,
        String companyLogo,
        String employmentType,
        BigDecimal minSalary,
        BigDecimal maxSalary,
        String salaryPeriod,
        List<String> seniority,
        String currency,
        List<String> locationRestrictions,
        List<Integer> timezoneRestrictions,
        List<String> categories,
        List<String> parentCategories,
        String description,
        Long pubDate,
        Long expiryDate,
        String applicationLink,
        String guid
) {
}