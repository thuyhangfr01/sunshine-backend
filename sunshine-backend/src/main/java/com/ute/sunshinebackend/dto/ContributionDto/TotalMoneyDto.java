package com.ute.sunshinebackend.dto.ContributionDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TotalMoneyDto {
    private BigDecimal totalMoney = BigDecimal.valueOf(0);
}
