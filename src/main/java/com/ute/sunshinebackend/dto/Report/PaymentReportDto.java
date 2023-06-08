package com.ute.sunshinebackend.dto.Report;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentReportDto {
    private String id;
    private BigDecimal amountMoney;
    private String userName;
    private String receiver;
    private String type;
    private Date createdAt;
    private Integer projectId;
}
