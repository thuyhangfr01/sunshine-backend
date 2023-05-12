package com.ute.sunshinebackend.repository.Contribution;

import com.ute.sunshinebackend.entity.Contribution.ContributionArtifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionArtifactRepository extends JpaRepository<ContributionArtifact, Long> {

}
