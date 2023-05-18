package com.ute.sunshinebackend.dto.ProjectDto;

import lombok.Data;

import java.util.*;

@Data
public class ProjectPaymentDto {
    private String id;
    private long amountMoney;
    private String reason;
    private Date createdAt;
    private String userName;
    private String projectName;

    public ProjectPaymentDto() {
    }

    public ProjectPaymentDto(String id, long amountMoney, String reason, Date createdAt, String userName, String projectName) {
        this.id = id;
        this.amountMoney = amountMoney;
        this.reason = reason;
        this.createdAt = createdAt;
        this.userName = userName;
        this.projectName = projectName;
    }
}
