package com.ute.sunshinebackend.dto.ContributionDto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ContributionArtifactDto {
    private String contributionId;
    private String artifactName;
    private Long donatedAmount;
    private Long receivedAmount;
    private String calculationUnit;
    private String artifactStatus;

    public ContributionArtifactDto(String contributionId, String artifactName, Long donatedAmount, Long receivedAmount, String calculationUnit, String artifactStatus) {
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
