package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.dto.ProjectDto;
import com.ute.sunshinebackend.entity.ERole;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.entity.Project.ProjectType;
import com.ute.sunshinebackend.entity.Role;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import com.ute.sunshinebackend.repository.Project.ProjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @Override
    public ResponseEntity<Project> addProject(Long idType, Project projectRequest) {
        Project project = projectTypeRepository.findById(idType).map(projectType -> {
            projectRequest.setProjectType(projectType);

            return projectRepository.save(projectRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Project type with id = " + idType));
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }
}
