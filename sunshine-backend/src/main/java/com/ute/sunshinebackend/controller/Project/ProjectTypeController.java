package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.entity.Project.ProjectType;
import com.ute.sunshinebackend.service.Project.ProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
@RequestMapping("/api")
public class ProjectTypeController {
    @Autowired
    ProjectTypeService projectTypeService;

    //api get all types
    @GetMapping("/project/types")
    public ResponseEntity<List<ProjectType>> getAllUsers(){
        return projectTypeService.getAllType();
    }

    //api get type by id
    @GetMapping("/project/type/{id}")
    public ResponseEntity<ProjectType> getUser(@PathVariable("id") long id){
        return projectTypeService.getTypeById(id);
    }

    //api add type
    @PostMapping("/project/type")
    public ResponseEntity<ProjectType> addType(@RequestBody ProjectType projectType){
        return projectTypeService.addType(projectType);
    }

    //api update type
    @PutMapping("/project/type/{id}")
    public ResponseEntity<ProjectType> updateType(@PathVariable("id") long id, @RequestBody ProjectType projectType){
        return projectTypeService.updateType(id, projectType);
    }

}
