package com.ute.sunshinebackend.dto.Report;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ContributionUserDto {
    private String id;
    private String projectName;
    private BigDecimal amountMoney;
    private String status;
    private Date createdAt;
}
