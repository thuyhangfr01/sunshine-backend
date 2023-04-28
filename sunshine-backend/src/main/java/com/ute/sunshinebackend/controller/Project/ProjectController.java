package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.dto.ProjectCreatorDto;
import com.ute.sunshinebackend.dto.ProjectListDto;
import com.ute.sunshinebackend.repository.Project.ProjectTypeRepository;
import com.ute.sunshinebackend.service.Project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectListDto>> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/type/{id}/projects")
    public ResponseEntity<List<ProjectListDto>> getProjectsByTypeId(@PathVariable("id") Long typeId){
        return projectService.getProjectsByTypeId(typeId);
    }

    @GetMapping("/status/{id}/projects")
    public ResponseEntity<List<ProjectListDto>> getProjectsByStatusId(@PathVariable("id") Long statusId){
        return projectService.getProjectsByTypeId(statusId);
    }

    @GetMapping("/latestProjects")
    public ResponseEntity<Page<ProjectListDto>> getTop5LatestProjects(){
        return projectService.getTop5LatestProjects(PageRequest.of(0, 5));
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectListDto> getProjectById(@PathVariable("id") Long id){
        return projectService.getProjectById(id);
    }

    @PostMapping("/type/{id}/project")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<ProjectCreatorDto> addProject(@PathVariable(value = "id") long id, @RequestBody ProjectCreatorDto projectCreatorDto){
        return projectService.addProject(id, projectCreatorDto);
    }

    @PutMapping("/project/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<ProjectCreatorDto> updateProject(@PathVariable(value = "id") long id, @RequestBody ProjectCreatorDto projectCreatorDto){
        return projectService.updateProject(id, projectCreatorDto);
    }

    @DeleteMapping("/project/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<Boolean> deleteProject(@PathVariable("id") Long id){
        return projectService.deleteProject(id);
    }
}
