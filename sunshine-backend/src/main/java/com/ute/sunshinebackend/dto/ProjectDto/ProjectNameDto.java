package com.ute.sunshinebackend.dto.ProjectDto;

import lombok.Data;

@Data
public class ProjectNameDto {
    private long projectId;
    private String projectName;

    public ProjectNameDto() {
    }

    public ProjectNameDto(long projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }
}
