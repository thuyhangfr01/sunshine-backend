package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionDto.ContributionCreatorDto;
import com.ute.sunshinebackend.dto.ContributionDto.ContributionDto;
import com.ute.sunshinebackend.dto.ContributionDto.ContributionMoneyUpdateDto;
import com.ute.sunshinebackend.dto.ContributionDto.StatusMoneyDto;
import org.springframework.http.ResponseEntity;

import java.util.*;

public interface ContributionService {
    //get all
    public ResponseEntity<List<ContributionDto>> getAllContributions();

    //get all latest contributions
    public ResponseEntity<List<ContributionDto>> getAllLatestContributions();

    //get contribution by id
    public ResponseEntity<ContributionDto> getContributionById(String contributionId);

    //get all contributions by user id
    public ResponseEntity<List<ContributionDto>> getAllContributionByUserId(Long userId);

    //get all contributions by project id
    public ResponseEntity<List<ContributionDto>> getAllContributionByProjectId(Long projectId);

    //create new contribution
    public ResponseEntity<ContributionCreatorDto> addContribution(ContributionCreatorDto contributionCreatorDto);

    //update amount money
    public ResponseEntity<ContributionMoneyUpdateDto> updateMoneyById(Long id, ContributionMoneyUpdateDto contributionMoneyUpdateDto);

    //update status
    public ResponseEntity<StatusMoneyDto> updateStatusMoney(Long contributionMoneyId, StatusMoneyDto statusMoneyDto);

    //delete contribution
    public ResponseEntity<Boolean> deleteContribution(String contributionId);
}
