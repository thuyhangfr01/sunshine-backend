package com.ute.sunshinebackend.dto.ProjectDto;

import com.ute.sunshinebackend.entity.Project.ProjectImage;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

@Data
public class ProjectCustomListDto {
    private Integer id;
    private String name;
    private String details;
    private BigDecimal minMoney;
//    private BigDecimal revMoney;
    private Date createdAt;
//    private List<ProjectImageCustomDto> imagesList;
}
