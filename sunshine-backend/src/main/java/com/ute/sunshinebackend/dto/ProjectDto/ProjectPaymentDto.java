package com.ute.sunshinebackend.dto.ProjectDto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

@Data
public class ProjectPaymentDto {
    private String id;
    private BigDecimal amountMoney;
    private String reason;
    private Date createdAt;
    private String userName;
    private String projectName;
    private String receiver;
    public ProjectPaymentDto() {
    }

    public ProjectPaymentDto(String id, BigDecimal amountMoney, String reason, Date createdAt, String userName, String projectName, String receiver) {
        this.id = id;
        this.amountMoney = amountMoney;
        this.reason = reason;
        this.createdAt = createdAt;
        this.userName = userName;
        this.projectName = projectName;
        this.receiver = receiver;
    }
}
