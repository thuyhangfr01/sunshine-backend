package com.ute.sunshinebackend.dto;

import lombok.Data;

import java.util.*;

@Data
public class ProjectListDto {
    private long projId;
    private String projName;
    private String details;
    private String typeName;
    private long numVolunteers;
    private String statusName;
    private String position;
    private Date startTime;
    private Date endTime;
    private Date holdTime;

//    private String imgUrl;
//    private String proofUrl;
//    private Set<String> projectMonies;
//    private Set<String> projectArtifacts;

//    ProjectMoney projectMonies;
//    ProjectArtifact projectArtifacts;

    public ProjectListDto() {
    }

    public ProjectListDto(long projId, String projName, String details, String typeName, long numVolunteers, String statusName, String position, Date startTime, Date endTime, Date holdTime) {
        this.projId = projId;
        this.projName = projName;
        this.details = details;
        this.typeName = typeName;
        this.numVolunteers = numVolunteers;
        this.statusName = statusName;
        this.position = position;
        this.startTime = startTime;
        this.endTime = endTime;
        this.holdTime = holdTime;
    }
}
