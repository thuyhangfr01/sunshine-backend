package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.dto.ProjectDto;
import com.ute.sunshinebackend.entity.Project.Project;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    public ResponseEntity<Project> addProject(Long idType, Project project);
}
