package com.ute.sunshinebackend.dto;

import lombok.Data;

@Data
public class ContributionMoneyCreatorDto {
    private long amount_money;

    private long statusMoneyId = 1;

    public ContributionMoneyCreatorDto(long amount_money, long statusMoneyId) {
        this.amount_money = amount_money;
        this.statusMoneyId = statusMoneyId;
    }

    public ContributionMoneyCreatorDto() {
    }
}
