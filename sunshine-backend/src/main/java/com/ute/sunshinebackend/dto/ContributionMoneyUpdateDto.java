package com.ute.sunshinebackend.dto;

import lombok.Data;

@Data
public class ContributionMoneyUpdateDto {
    public long moneyId;
    public long amountMoney;

    public ContributionMoneyUpdateDto() {
    }

    public ContributionMoneyUpdateDto(long moneyId, long amountMoney) {
        this.moneyId = moneyId;
        this.amountMoney = amountMoney;
    }
}
