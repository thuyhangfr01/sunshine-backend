package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.dto.ProjectDto;
import com.ute.sunshinebackend.dto.ProjectJoinDto;
import com.ute.sunshinebackend.repository.Project.ProjectTypeRepository;
import com.ute.sunshinebackend.service.Project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @PostMapping("/type/{id}/project")
    public ResponseEntity<ProjectDto> addProject(@PathVariable(value = "id") long id, @RequestBody ProjectDto projectDto){
        return projectService.addProject(id, projectDto);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectJoinDto>> getProjectInnerJoin(){
        return projectService.getAllProjects();
    }
}
