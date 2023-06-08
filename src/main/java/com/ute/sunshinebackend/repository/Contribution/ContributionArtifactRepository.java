package com.ute.sunshinebackend.repository.Contribution;

import com.ute.sunshinebackend.entity.Contribution.ContributionArtifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionArtifactRepository extends JpaRepository<ContributionArtifact, Long> {
    @Query(value =
            "SELECT c.id, u.name as 'userName', p.name, c.created_at " +
                    "FROM contributions as c, contribution_artifacts as ca, projects as p, users as u " +
                    "WHERE c.id = ca.id_contribution and c.id_project = p.id and c.id_user = u.id " +
                    "   and c.id IN (select ca1.id_contribution from contribution_artifacts as ca1) " +
                    "GROUP BY c.id, u.name, p.name, c.created_at " +
                    "ORDER BY c.created_at DESC",
            nativeQuery = true)
    List listContributionArtifacts();

    @Query(value =
            "SELECT c.id, u.name as 'userName', p.name, c.created_at " +
                    "FROM contributions as c, contribution_artifacts as ca, projects as p, users as u " +
                    "WHERE c.id = ca.id_contribution and c.id_project = p.id and c.id_user = u.id " +
                    "   and c.id IN (select ca1.id_contribution from contribution_artifacts as ca1) and c.id_user = :userId " +
                    "GROUP BY c.id, u.name, p.name, c.created_at " +
                    "ORDER BY c.created_at DESC",
            nativeQuery = true)
    List listContributionArtifactsByUserId(Long userId);

    @Query(value =
            "SELECT c.id, ca.id as 'artifactId', ca.artifact_name, ca.donated_amount, ca.received_amount, ca.calculation_unit, cs.name " +
                    "FROM contributions as c, contribution_artifacts as ca, contribution_status as cs " +
                    "WHERE c.id = ca.id_contribution and ca.id_status = cs.id and c.id = :contributionId ",
            nativeQuery = true)
    List listArtifactsByContributionId(String contributionId);
}
