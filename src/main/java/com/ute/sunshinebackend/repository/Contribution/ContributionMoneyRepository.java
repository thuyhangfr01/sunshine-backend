package com.ute.sunshinebackend.repository.Contribution;

import com.ute.sunshinebackend.entity.Contribution.ContributionMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionMoneyRepository extends JpaRepository<ContributionMoney, Long> {
}
