package com.ute.sunshinebackend.dto.ProjectDto;

import lombok.Data;

@Data
public class ProjectTypeDto {
    private Long id;
    private String name;

    public ProjectTypeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
