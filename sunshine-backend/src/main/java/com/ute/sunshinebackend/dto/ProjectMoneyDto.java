package com.ute.sunshinebackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectMoneyDto {
    private BigDecimal minMoney;

    public ProjectMoneyDto() {

    }

    public ProjectMoneyDto(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }
}
