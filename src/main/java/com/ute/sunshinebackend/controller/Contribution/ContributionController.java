package com.ute.sunshinebackend.controller.Contribution;

import com.ute.sunshinebackend.dto.ContributionDto.*;
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
    private ResponseEntity<ContributionDto> getContributionById(@PathVariable("id") String contributionId){
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

    @GetMapping("/contribution/project/{id}/status")
    private ResponseEntity<List<ContributionDto>> getAllContributionByProjectIdByStatus(@PathVariable("id") Long projectId){
        return contributionService.getAllContributionByProjectIdByStatus(projectId);
    }

    @PostMapping("/contribution")
    private ResponseEntity<ContributionCreatorDto> addContribution(@RequestBody ContributionCreatorDto contributionCreatorDto){
        return contributionService.addContribution(contributionCreatorDto);
    }

    @PostMapping("/contributionImport")
    private ResponseEntity<ContributionImportDto> importContribution(@RequestBody ContributionImportDto contributionImportDto){
        return contributionService.importContribution(contributionImportDto);
    }

    @PutMapping("/contribution/amountMoney/{mId}")
    private ResponseEntity<ContributionMoneyUpdateDto> updateMoneyById(@PathVariable("mId") Long mId, @RequestBody ContributionMoneyUpdateDto contributionMoneyUpdateDto){
        return contributionService.updateMoneyById(mId, contributionMoneyUpdateDto);
    }

    @PutMapping("/contribution/money/{moneyId}/status") //duyet don
    private ResponseEntity<StatusMoneyDto> updateContributionMoneyStatus(@PathVariable("moneyId") Long contributionMoneyId, @RequestBody StatusMoneyDto statusMoneyDto){
        return contributionService.updateStatusMoney(contributionMoneyId, statusMoneyDto);
    }

    @DeleteMapping("/contribution/{id}")
    private ResponseEntity<Boolean> deleteContributionById(@PathVariable("id") String contributionId){
        return contributionService.deleteContribution(contributionId);
    }
}
