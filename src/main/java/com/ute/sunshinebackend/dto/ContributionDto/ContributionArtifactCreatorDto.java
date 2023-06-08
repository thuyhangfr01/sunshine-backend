package com.ute.sunshinebackend.dto.ContributionDto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ContributionArtifactCreatorDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String artifactName;
    private long donatedAmount;
    private String calculationUnit;
    private long statusArtifactId = 1;

    public ContributionArtifactCreatorDto(long id, String artifactName, long donatedAmount, String calculationUnit, long statusArtifactId) {
        this.id = id;
        this.artifactName = artifactName;
        this.donatedAmount = donatedAmount;
        this.calculationUnit = calculationUnit;
        this.statusArtifactId = statusArtifactId;
    }

    public ContributionArtifactCreatorDto() {
    }
}
