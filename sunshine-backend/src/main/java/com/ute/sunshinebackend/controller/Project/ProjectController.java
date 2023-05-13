package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.dto.ProjectCreatorDto;
import com.ute.sunshinebackend.dto.ProjectListDto;
import com.ute.sunshinebackend.dto.ProjectNameDto;
import com.ute.sunshinebackend.entity.Project.Project;
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

    @GetMapping("/listProjectName")
    public ResponseEntity<List<ProjectNameDto>> getListProjectName(){
        return projectService.getListProjectName();
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id){
        return projectService.getProjectById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getProjects(@RequestParam(required = false) String name,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "6") int size){
        return projectService.getAll(name, page, size);
    }

    @GetMapping("/allProjects")
    public ResponseEntity<List<Project>> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/type/projects")
    public ResponseEntity<Map<String, Object>> getProjectsByTypeId(@RequestParam Long id,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "6") int size){
        return projectService.getProjectsByTypeId(id, page, size);
    }

    @GetMapping("/status/projects")
    public ResponseEntity<Map<String, Object>> getProjectByStatusId(@RequestParam Long id,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "6") int size){
        return projectService.getProjectsByStatusId(id, page, size);
    }

//    @GetMapping("/latestProjects")
//    public ResponseEntity<Page<ProjectListDto>> getTop5LatestProjects(){
//        return projectService.getTop5LatestProjects(PageRequest.of(0, 5));
//    }

    @GetMapping("/top5LatestProject")
    public ResponseEntity<Page<Project>> getTop5LatestProject(){
        return projectService.getTop5LatestProject(PageRequest.of(0, 5));
    }

    @GetMapping("/latestProject")
    public ResponseEntity<List<Project>> getLatestProject(@RequestParam(required = false) String name){
        return projectService.getLatestProject(name);
    }

    @PostMapping("/project")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<ProjectCreatorDto> addProject(@RequestBody ProjectCreatorDto projectCreatorDto){
        return projectService.addProject(projectCreatorDto);
    }

    @PutMapping("/project/{id}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<ProjectCreatorDto> updateProject(@PathVariable(value = "id") long id, @RequestBody ProjectCreatorDto projectCreatorDto){
        return projectService.updateProject(id, projectCreatorDto);
    }

    @DeleteMapping("/project/{id}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<Boolean> deleteProject(@PathVariable("id") Long id){
        return projectService.deleteProject(id);
    }
}
