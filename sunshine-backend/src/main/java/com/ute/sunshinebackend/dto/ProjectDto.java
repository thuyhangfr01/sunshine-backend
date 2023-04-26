package com.ute.sunshinebackend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDto {
    private String name;
    private String details;
    private Long idType;
    private Long numVolunteers;
    private Date startTime;
    private Date endTime;
    private Date holdTime;
    private String position;
    private Long idWard;

    public ProjectDto(String name, String details, Long idType, Long numVolunteers, Date startTime, Date endTime, Date holdTime, String position, Long idWard) {
        this.name = name;
        this.details = details;
        this.idType = idType;
        this.numVolunteers = numVolunteers;
        this.startTime = startTime;
        this.endTime = endTime;
        this.holdTime = holdTime;
        this.position = position;
        this.idWard = idWard;
    }
}
