package com.ute.sunshinebackend.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ContributionArtifactDto {
    private long contributionId;
    private String artifactName;
    private Long donatedAmount;
    private Long receivedAmount;
    private String calculationUnit;
    private String artifactStatus;

    public ContributionArtifactDto(long contributionId, String artifactName, Long donatedAmount, Long receivedAmount, String calculationUnit, String artifactStatus) {
        this.contributionId = contributionId;
        this.artifactName = artifactName;
        this.donatedAmount = donatedAmount;
        this.receivedAmount = receivedAmount;
        this.calculationUnit = calculationUnit;
        this.artifactStatus = artifactStatus;
    }

    public ContributionArtifactDto() {
    }
}
