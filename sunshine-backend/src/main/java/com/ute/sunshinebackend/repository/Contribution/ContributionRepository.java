package com.ute.sunshinebackend.repository.Contribution;

import com.ute.sunshinebackend.entity.Contribution.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, String>, JpaSpecificationExecutor<Contribution> {
    boolean existsById(Long contributionId);

    List<Contribution> findByOrderByCreatedAtDesc();

    @Query(value = "SELECT c.* FROM contributions c, users u WHERE c.id_user = u.id and c.id_user = :idUser", nativeQuery = true)
    List<Contribution> findByUserId(Long idUser);

    @Query(value = "SELECT c.* FROM contributions c, projects p WHERE c.id_project = p.id and c.id_project = :idProject", nativeQuery = true)
    List<Contribution> findByProjectId(Long idProject);

    @Query(nativeQuery = true, value = "SELECT DBO.AUTO_IDC()")
    String geneId();

}
