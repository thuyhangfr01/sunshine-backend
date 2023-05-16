package com.ute.sunshinebackend.dto;

import com.ute.sunshinebackend.entity.Contribution.ContributionMoney;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class ContributionCreatorDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long projectId;
    private String nickname;
    private String projectName = "";
    private String messages;

    private ContributionMoney contributionMoney;

    private long amountMoney;
    private long statusMoneyId = 1;

    public ContributionCreatorDto() {}

    public ContributionCreatorDto(Long id, Long userId, Long projectId, String nickname, String projectName, String messages, ContributionMoney contributionMoney, long amountMoney, long statusMoneyId) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
        this.nickname = nickname;
        this.projectName = projectName;
        this.messages = messages;
        this.contributionMoney = contributionMoney;
        this.amountMoney = amountMoney;
        this.statusMoneyId = statusMoneyId;
    }
}
