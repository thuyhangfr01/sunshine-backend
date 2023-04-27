package com.ute.sunshinebackend.repository.Project;

import com.ute.sunshinebackend.entity.Project.ProjectArtifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository
public interface ProjectArtifactRepository extends JpaRepository<ProjectArtifact, Long> {
    List<ProjectArtifact> findByProjectId(Long projectId);

    @Transactional
    void deleteByProjectId(Long projectId);

}
