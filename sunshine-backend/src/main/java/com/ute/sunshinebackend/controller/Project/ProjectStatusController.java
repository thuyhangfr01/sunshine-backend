package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.entity.Project.ProjectStatus;
import com.ute.sunshinebackend.service.Project.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjectStatusController {
    @Autowired
    ProjectStatusService projectStatusService;

    //api get all types
    @GetMapping("/project/status")
    public ResponseEntity<List<ProjectStatus>> getAllStatus(){
        return projectStatusService.getAllStatus();
    }
}
