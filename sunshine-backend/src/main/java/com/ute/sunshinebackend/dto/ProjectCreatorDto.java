package com.ute.sunshinebackend.dto;

import lombok.Data;

import java.util.*;

@Data
public class ProjectCreatorDto {
    private String name;
    private String details;
    private long numVolunteers;
    private long typeId;
    private long statusId = 1;
    private String position;
    private Date startTime;
    private Date endTime;
    private Date holdTime;

    public ProjectCreatorDto(String name, String details, long numVolunteers, long statusId, String position, Date startTime, Date endTime, Date holdTime) {
        this.name = name;
        this.details = details;
        this.numVolunteers = numVolunteers;
        this.statusId = statusId;
        this.position = position;
        this.startTime = startTime;
        this.endTime = endTime;
        this.holdTime = holdTime;
    }

    public ProjectCreatorDto(String name, String details, long numVolunteers, long typeId, long statusId, String position, Date startTime, Date endTime, Date holdTime) {
        this.name = name;
        this.details = details;
        this.numVolunteers = numVolunteers;
        this.typeId = typeId;
        this.statusId = statusId;
        this.position = position;
        this.startTime = startTime;
        this.endTime = endTime;
        this.holdTime = holdTime;
    }

    public ProjectCreatorDto() {
    }
}
