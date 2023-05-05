package com.ute.sunshinebackend.dto;

import com.ute.sunshinebackend.entity.Project.ProjectImage;
import lombok.Data;

import java.util.*;

@Data
public class ProjectListDto {
    private Long projId;
    private String projName;
    private String details;
    private String typeName;
    private Long numVolunteers;
    private String statusName;
    private String position;
    private Date startTime;
    private Date endTime;
    private Date holdTime;

    private String imgUrl;

    public ProjectListDto() {
    }

    public ProjectListDto(Long projId, String projName, String details, String typeName, Long numVolunteers, String statusName, String position, Date startTime, Date endTime, Date holdTime) {
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

    public ProjectListDto(Long projId, String projName, String details, String typeName, Long numVolunteers, String statusName, String position, Date startTime, Date endTime, Date holdTime, String imgUrl) {
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
        this.imgUrl = imgUrl;
    }
}
