package com.dinesh.ai_job_hunter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "external_jobs",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_external_job_guid",
                        columnNames = "guid"
                )
        }
)
public class ExternalJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 1000)
    private String guid;

    @Column(nullable = false)
    private String title;

    private String companyName;

    @Column(length = 2000)
    private String applicationLink;

    private Instant publishedAt;

    public ExternalJob(
            String guid,
            String title,
            String companyName,
            String applicationLink,
            Instant publishedAt) {
        this.guid = guid;
        this.title = title;
        this.companyName = companyName;
        this.applicationLink = applicationLink;
        this.publishedAt = publishedAt;
    }

}