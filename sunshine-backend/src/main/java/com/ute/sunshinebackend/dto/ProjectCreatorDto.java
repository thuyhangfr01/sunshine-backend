package com.ute.sunshinebackend.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Data
public class ProjectCreatorDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String details;
    private long numVolunteers;
    private long typeId;
    private long statusId = 1;
    private String position;
    private Date startTime;
    private Date endTime;
    private Date holdTime;
    private Date updatedAt = Timestamp.valueOf(LocalDateTime.now());

    public ProjectCreatorDto(long id, String name, String details, long numVolunteers, long typeId, long statusId, String position, Date startTime, Date endTime, Date holdTime) {
        this.id = id;
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

    public ProjectCreatorDto(String name, String details, long numVolunteers, long typeId, long statusId, String position, Date startTime, Date endTime, Date holdTime, Date updatedAt) {
        this.name = name;
        this.details = details;
        this.numVolunteers = numVolunteers;
        this.typeId = typeId;
        this.statusId = statusId;
        this.position = position;
        this.startTime = startTime;
        this.endTime = endTime;
        this.holdTime = holdTime;
        this.updatedAt = updatedAt;
    }

    public ProjectCreatorDto() {
    }
}
