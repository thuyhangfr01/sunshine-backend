package com.ute.sunshinebackend.repository.Project;

import com.ute.sunshinebackend.entity.Project.ProjectMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProjectMoneyRepository extends JpaRepository<ProjectMoney, Long> {
    List<ProjectMoney> findByProjectId(Long projectId);
}
