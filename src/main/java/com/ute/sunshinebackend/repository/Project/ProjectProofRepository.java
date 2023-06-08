package com.ute.sunshinebackend.repository.Project;

import com.ute.sunshinebackend.entity.Project.ProjectProof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository
public interface ProjectProofRepository extends JpaRepository<ProjectProof, Long> {
    List<ProjectProof> findByProjectId(Long projectId);

    @Transactional
    void deleteByProjectId(Long projectId);
}
