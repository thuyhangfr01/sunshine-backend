package com.ute.sunshinebackend.dto.ContributionDto;

import com.ute.sunshinebackend.entity.Contribution.ContributionStatus;
import lombok.Data;

@Data
public class StatusArtifactDto {
    private long artifactId;
    private long statusId;
    private long receivedAmount = 0;
    private ContributionStatus contributionStatus;

    public StatusArtifactDto(long artifactId, long statusId, long receivedAmount, ContributionStatus contributionStatus) {
        this.artifactId = artifactId;
        this.statusId = statusId;
        this.receivedAmount = receivedAmount;
        this.contributionStatus = contributionStatus;
    }
}
