package com.ute.sunshinebackend.dto;

import lombok.Data;
import java.util.List;

@Data
public class ContributionCreatorDto {
    private Long userId;
    private Long projectId;
    private String nickname;
    private String messages;
    private Long amount_money;
    private List<ContributionArtifactCreatorDto> contributionArtifactCreatorDto;

    public ContributionCreatorDto() {}

    public ContributionCreatorDto(Long userId, Long projectId, String nickname, String messages, Long amount_money, List<ContributionArtifactCreatorDto> contributionArtifactCreatorDto) {
        this.userId = userId;
        this.projectId = projectId;
        this.nickname = nickname;
        this.messages = messages;
        this.amount_money = amount_money;
        this.contributionArtifactCreatorDto = contributionArtifactCreatorDto;
    }
}
