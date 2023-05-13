package com.ute.sunshinebackend.repository.Contribution;

import com.ute.sunshinebackend.entity.Contribution.ContributionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionStatusRepository extends JpaRepository<ContributionStatus, Long> {
}
