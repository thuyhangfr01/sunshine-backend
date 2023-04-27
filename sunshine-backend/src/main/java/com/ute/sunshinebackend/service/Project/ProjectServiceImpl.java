package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.dto.ProjectDto;
import com.ute.sunshinebackend.dto.ProjectJoinDto;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import com.ute.sunshinebackend.repository.Project.ProjectTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<ProjectJoinDto>> getAllProjects() {
        List<ProjectJoinDto> list = projectRepository.getAllProjects();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectDto> addProject(Long idType, ProjectDto projectDtoRequest) {
        //convert dto to entity
        Project projectRequest = modelMapper.map(projectDtoRequest, Project.class);

        Project project = projectTypeRepository.findById(idType).map(projectType -> {
            projectRequest.setProjectType(projectType);

            return projectRepository.save(projectRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Project type with id = " + idType));

        //convert entity to dto
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);

        return new ResponseEntity<>(projectDto, HttpStatus.CREATED);
    }

}
