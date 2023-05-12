package com.ute.sunshinebackend.controller.Contribution;

import com.ute.sunshinebackend.dto.ContributionArtifactDto;
import com.ute.sunshinebackend.service.Contribution.ContributionArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ContributionArtifactController {
    @Autowired
    ContributionArtifactService contributionArtifactService;

    @GetMapping("/contribution/{id}/artifacts")
    public ResponseEntity<List<ContributionArtifactDto>> getArtifactsByContributionId(@PathVariable("id") Long contributionId){
        return contributionArtifactService.getArtifactsByContributionId(contributionId);
    }
}
