package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionArtifactDto;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface ContributionArtifactService {
    public ResponseEntity<List<ContributionArtifactDto>> getArtifactsByContributionId(Long contributionId);
}
