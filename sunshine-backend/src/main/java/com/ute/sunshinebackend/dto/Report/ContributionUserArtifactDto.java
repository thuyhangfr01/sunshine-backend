package com.ute.sunshinebackend.dto.Report;

import lombok.Data;

import java.util.Date;

@Data
public class ContributionUserArtifactDto {
    private String id;
    private String projectName;
    private String artifactName;
    private Integer donatedAmount;
    private String status;
    private Date createdAt;
}
