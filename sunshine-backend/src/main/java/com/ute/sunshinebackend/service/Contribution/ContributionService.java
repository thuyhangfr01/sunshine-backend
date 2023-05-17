package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionCreatorDto;
import com.ute.sunshinebackend.dto.ContributionDto;
import com.ute.sunshinebackend.dto.ContributionMoneyUpdateDto;
import com.ute.sunshinebackend.dto.StatusMoneyDto;
import com.ute.sunshinebackend.entity.Contribution.ContributionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
