package com.ute.sunshinebackend.dto;

import lombok.Data;

@Data
public class ContributionArtifactDto {
    private String artifactName;
    private Long donatedAmount;
    private Long receivedAmount;
    private String calculationUnit;
    private String artifactStatus;

    public ContributionArtifactDto(String artifactName, Long donatedAmount, Long receivedAmount, String calculationUnit, String artifactStatus) {
        this.artifactName = artifactName;
        this.donatedAmount = donatedAmount;
        this.receivedAmount = receivedAmount;
        this.calculationUnit = calculationUnit;
        this.artifactStatus = artifactStatus;
    }

    public ContributionArtifactDto() {
    }
}
