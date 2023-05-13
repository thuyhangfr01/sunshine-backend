package com.ute.sunshinebackend.controller.Contribution;

import com.ute.sunshinebackend.dto.ContributionCreatorDto;
import com.ute.sunshinebackend.dto.ContributionDto;
import com.ute.sunshinebackend.dto.ContributionMoneyUpdateDto;
import com.ute.sunshinebackend.entity.Contribution.ContributionStatus;
import com.ute.sunshinebackend.service.Contribution.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ContributionController {
    @Autowired
    ContributionService contributionService;

    @GetMapping("/contributions")
    private ResponseEntity<List<ContributionDto>> getAllContributions(){
        return contributionService.getAllContributions();
    }

    @GetMapping("/latestContributions")
    private ResponseEntity<List<ContributionDto>> getAllLatestContributions(){
        return contributionService.getAllLatestContributions();
    }

    @GetMapping("/contribution/{id}")
    private ResponseEntity<ContributionDto> getContributionById(@PathVariable("id") Long contributionId){
        return contributionService.getContributionById(contributionId);
    }

    @GetMapping("/contribution/user/{id}")
    private ResponseEntity<List<ContributionDto>> getAllContributionByUserId(@PathVariable("id") Long userId){
        return contributionService.getAllContributionByUserId(userId);
    }

    @GetMapping("/contribution/project/{id}")
    private ResponseEntity<List<ContributionDto>> getAllContributionByProjectId(@PathVariable("id") Long projectId){
        return contributionService.getAllContributionByProjectId(projectId);
    }

    @PostMapping("/contribution")
    private ResponseEntity<ContributionCreatorDto> addContribution(@RequestBody ContributionCreatorDto contributionCreatorDto){
        return contributionService.addContribution(contributionCreatorDto);
    }

    @PutMapping("/contribution/{id}/amountMoney")
    private ResponseEntity<ContributionMoneyUpdateDto> updateMoneyById(@PathVariable("id") Long contributionId, ContributionMoneyUpdateDto contributionMoneyUpdateDto){
        return contributionService.updateMoneyById(contributionId, contributionMoneyUpdateDto);
    }

    @PutMapping("/contribution/money/{id}/status") //duyet don
    private ResponseEntity<ContributionStatus> updateContributionMoneyStatus(@PathVariable("id") Long contributionMoneyId, @RequestBody ContributionStatus contributionStatus){
        return contributionService.updateStatusMoney(contributionMoneyId, contributionStatus);
    }

    @DeleteMapping("/contribution/{id}")
    private ResponseEntity<Boolean> deleteContributionById(@PathVariable("id") Long contributionId){
        return contributionService.deleteContribution(contributionId);
    }
}
