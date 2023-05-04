package com.ute.sunshinebackend.dto;

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
