package com.ute.sunshinebackend.dto;

import lombok.Data;

@Data
public class ContributionArtifactCreatorDto {
    private String artifactName;
    private Long donatedAmount;
    private String calculationUnit;

    public ContributionArtifactCreatorDto() {
    }

    public ContributionArtifactCreatorDto(String artifactName, Long donatedAmount, String calculationUnit) {
        this.artifactName = artifactName;
        this.donatedAmount = donatedAmount;
        this.calculationUnit = calculationUnit;
    }
}
