package com.ute.sunshinebackend.repository.Project;

import com.ute.sunshinebackend.controller.Project.ProjectController;
import com.ute.sunshinebackend.entity.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByProjectType(Long idType);
}
