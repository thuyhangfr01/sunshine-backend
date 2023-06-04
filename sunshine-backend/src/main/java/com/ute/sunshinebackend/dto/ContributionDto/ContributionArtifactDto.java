package com.ute.sunshinebackend.dto.ContributionDto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ContributionArtifactDto {
    private String contributionId;
    private Integer artifactId;
    private String artifactName;
    private Integer donatedAmount;
    private Integer receivedAmount;
    private String calculationUnit;
    private String artifactStatus;

    public ContributionArtifactDto(String contributionId, Integer artifactId, String artifactName, Integer donatedAmount, Integer receivedAmount, String calculationUnit, String artifactStatus) {
        this.contributionId = contributionId;
        this.artifactId = artifactId;
        this.artifactName = artifactName;
        this.donatedAmount = donatedAmount;
        this.receivedAmount = receivedAmount;
        this.calculationUnit = calculationUnit;
        this.artifactStatus = artifactStatus;
    }

    public ContributionArtifactDto() {
    }
}
