package com.ute.sunshinebackend.dto;

import lombok.Data;

@Data
public class ContributionMoneyUpdateDto {
    public long amountMoney;

    public ContributionMoneyUpdateDto() {
    }

    public ContributionMoneyUpdateDto(long amountMoney) {
        this.amountMoney = amountMoney;
    }
}
