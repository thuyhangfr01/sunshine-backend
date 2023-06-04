package com.ute.sunshinebackend.dto.ContributionDto;

import lombok.Data;
import java.util.Date;

@Data
public class ContributionArtifactListDto {
    private String id;
    private String userName;
    private String projectName;
    private Date createdAt;
}
