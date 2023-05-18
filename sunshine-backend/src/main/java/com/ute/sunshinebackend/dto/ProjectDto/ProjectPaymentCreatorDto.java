package com.ute.sunshinebackend.dto.ProjectDto;

import lombok.Data;

@Data
public class ProjectPaymentCreatorDto {
    private long amountMoney;
    private String reason;
    private long projectId;
    private long userId;

    public ProjectPaymentCreatorDto() {
    }

    public ProjectPaymentCreatorDto(long amountMoney, String reason, long projectId, long userId) {
        this.amountMoney = amountMoney;
        this.reason = reason;
        this.projectId = projectId;
        this.userId = userId;
    }
}
