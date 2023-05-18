package com.ute.sunshinebackend.dto.ContributionDto;

import com.ute.sunshinebackend.entity.Contribution.ContributionStatus;
import lombok.Data;

@Data
public class StatusMoneyDto {
    private long moneyId;
    private long statusId;
    private ContributionStatus contributionStatus;

    public StatusMoneyDto(long moneyId, long statusId, ContributionStatus contributionStatus) {
        this.moneyId = moneyId;
        this.statusId = statusId;
        this.contributionStatus = contributionStatus;
    }

    public StatusMoneyDto() {
    }
}
