package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionDto.ContributionArtifactCreatorDto;
import com.ute.sunshinebackend.dto.ContributionDto.ContributionArtifactListDto;
import com.ute.sunshinebackend.dto.ContributionDto.ContributionArtifactDto;
import com.ute.sunshinebackend.dto.ContributionDto.StatusArtifactDto;
import com.ute.sunshinebackend.entity.Contribution.ContributionArtifact;
import org.springframework.http.ResponseEntity;

import java.util.*;

public interface ContributionArtifactService {
    public ResponseEntity<List<ContributionArtifactListDto>> getContributionArtifacts();
    public ResponseEntity<List<ContributionArtifactListDto>> getContributionArtifactsByUserId(Long userId);
    public ResponseEntity<List<ContributionArtifactDto>> getArtifactsByContributionId(String contributionId);
    public ResponseEntity<ContributionArtifactCreatorDto> addNewArtifactByContributionId(String contributionId, ContributionArtifactCreatorDto contributionArtifactCreatorDto);
    public ResponseEntity<StatusArtifactDto> updateArtifactById(Long artifactId, StatusArtifactDto statusArtifactDto);
    public ResponseEntity<Boolean> deleteArtifactById(Long artifactId);
}
