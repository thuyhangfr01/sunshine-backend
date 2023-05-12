package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionCreatorDto;
import com.ute.sunshinebackend.dto.ContributionDto;
import com.ute.sunshinebackend.entity.Contribution.Contribution;
import org.springframework.http.ResponseEntity;

import java.util.*;

public interface ContributionService {
    //get all
    public ResponseEntity<List<ContributionDto>> getAllContributions();

    //get contribution by id
    public ResponseEntity<ContributionDto> getContributionById(Long contributionId);

    //get all contributions by user id
    public ResponseEntity<List<ContributionDto>> getAllContributionByUserId(Long userId);

    //get all contributions by project id
    public ResponseEntity<List<ContributionDto>> getAllContributionByProjectId(Long projectId);

    //create new contribution
    public ResponseEntity<ContributionCreatorDto> addContribution(ContributionCreatorDto contributionCreatorDto);
}
