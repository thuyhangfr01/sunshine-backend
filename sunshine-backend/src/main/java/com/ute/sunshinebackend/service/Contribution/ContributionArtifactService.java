package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionArtifactCreatorDto;
import com.ute.sunshinebackend.dto.ContributionArtifactDto;
import com.ute.sunshinebackend.entity.Contribution.ContributionArtifact;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

public interface ContributionArtifactService {
    //get all artifacts by contribution id
    public ResponseEntity<List<ContributionArtifactDto>> getArtifactsByContributionId(String contributionId);

    //create new actifact by contribution id
    public ResponseEntity<ContributionArtifactCreatorDto> addNewArtifactByContributionId(String contributionId, ContributionArtifactCreatorDto contributionArtifactCreatorDto);

    //update artifact by id
    public ResponseEntity<ContributionArtifact> updateArtifactById(Long artifactId, ContributionArtifact contributionArtifact);

    //delete artifact by id
    public ResponseEntity<Boolean> deleteArtifactById(Long artifactId);
}
