package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionArtifactCreatorDto;
import com.ute.sunshinebackend.dto.ContributionArtifactDto;
import com.ute.sunshinebackend.entity.Contribution.ContributionArtifact;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface ContributionArtifactService {
    //get all artifacts by contribution id
    public ResponseEntity<List<ContributionArtifactDto>> getArtifactsByContributionId(Long contributionId);

    //create new actifact by contribution id
    public ResponseEntity<ContributionArtifactCreatorDto> addNewArtifactByContributionId(Long contributionId, ContributionArtifactCreatorDto contributionArtifactCreatorDto);

    //update artifact by id
    public ResponseEntity<ContributionArtifact> updateArtifactById(Long artifactId, ContributionArtifact contributionArtifact);
}
