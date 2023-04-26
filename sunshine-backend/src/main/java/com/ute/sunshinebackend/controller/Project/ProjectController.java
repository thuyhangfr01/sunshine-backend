package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.dto.ProjectDto;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.repository.Project.ProjectTypeRepository;
import com.ute.sunshinebackend.service.Project.ProjectService;
import com.ute.sunshinebackend.service.Project.ProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @PostMapping("/type={id}/add")
    public ResponseEntity<Project> addProject(@PathVariable(value = "id") long id,  @RequestBody Project project){
        return projectService.addProject(id, project);
    }
}
