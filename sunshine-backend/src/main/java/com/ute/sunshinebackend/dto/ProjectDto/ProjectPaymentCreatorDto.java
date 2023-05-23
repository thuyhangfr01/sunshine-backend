package com.ute.sunshinebackend.dto.ProjectDto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ProjectPaymentCreatorDto {
    private long amountMoney;
    private String reason;
    private long projectId;
    private long userId;
    private Date createdAt = Timestamp.valueOf(LocalDateTime.now());
    private String userName;
    public ProjectPaymentCreatorDto() {
    }

    public ProjectPaymentCreatorDto(long amountMoney, String reason, long projectId, long userId, Date createdAt) {
        this.amountMoney = amountMoney;
        this.reason = reason;
        this.projectId = projectId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public ProjectPaymentCreatorDto(long amountMoney, String reason, long projectId, long userId, Date createdAt, String userName) {
        this.amountMoney = amountMoney;
        this.reason = reason;
        this.projectId = projectId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.userName = userName;
    }
}
