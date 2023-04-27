package com.ute.sunshinebackend.dto;

import lombok.Data;

@Data
public class ProjectArtifactDto {
    private String artifactName;
    private Long minQuantity;
    private String receivingAddress;

    public ProjectArtifactDto() {
    }

    public ProjectArtifactDto(String artifactName, Long minQuantity, String receivingAddress) {
        this.artifactName = artifactName;
        this.minQuantity = minQuantity;
        this.receivingAddress = receivingAddress;
    }
}
