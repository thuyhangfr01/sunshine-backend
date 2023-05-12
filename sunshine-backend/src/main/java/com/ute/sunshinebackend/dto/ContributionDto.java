package com.ute.sunshinebackend.dto;

import lombok.Data;
import java.util.List;

@Data
public class ContributionDto {
    private Long id;
    private String nickname;
    private String messages;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String projectName;
    private String projectType;
    private Long contributionMoney;
    private String moneyStatus;
    private List<ContributionArtifactDto> contributionArtifactDto;

    public ContributionDto() {
    }

    public ContributionDto(Long id, String nickname, String messages, String userName, String userEmail, String userPhone, String projectName, String projectType, Long contributionMoney, String moneyStatus, List<ContributionArtifactDto> contributionArtifactDto) {
        this.id = id;
        this.nickname = nickname;
        this.messages = messages;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.projectName = projectName;
        this.projectType = projectType;
        this.contributionMoney = contributionMoney;
        this.moneyStatus = moneyStatus;
        this.contributionArtifactDto = contributionArtifactDto;
    }

    public ContributionDto(Long id, String nickname, String messages, String projectName, String projectType, Long contributionMoney, String moneyStatus, List<ContributionArtifactDto> contributionArtifactDto) {
        this.id = id;
        this.nickname = nickname;
        this.messages = messages;
        this.projectName = projectName;
        this.projectType = projectType;
        this.contributionMoney = contributionMoney;
        this.moneyStatus = moneyStatus;
        this.contributionArtifactDto = contributionArtifactDto;
    }
}
