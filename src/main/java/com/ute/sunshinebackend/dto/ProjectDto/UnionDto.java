package com.ute.sunshinebackend.dto.ProjectDto;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class UnionDto {
    private String id;
    private BigDecimal amountMoney;
    private String userName;
    private String type;
    private Date createdAt;
    private Integer projectId;
    private String receiver;
}
