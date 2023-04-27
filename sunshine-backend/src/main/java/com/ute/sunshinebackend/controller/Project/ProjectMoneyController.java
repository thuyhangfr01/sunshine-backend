package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.entity.Project.ProjectMoney;
import com.ute.sunshinebackend.service.Project.ProjectMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
@RequestMapping("/api")
public class ProjectMoneyController {
    @Autowired
    ProjectMoneyService projectMoneyService;

    @GetMapping("/project/{id}/money")
    public ResponseEntity<List<ProjectMoney>> getMoney(@PathVariable("id") Long projectId){
        return  projectMoneyService.getMoney(projectId);
    }

    @PostMapping("/project/{id}/money")
    public ResponseEntity<ProjectMoney> addMoney(@PathVariable("id") Long projectId, @RequestBody ProjectMoney projectMoney){
        return projectMoneyService.addMoney(projectId, projectMoney);
    }

    @PutMapping("/project/money/{id}")
    public ResponseEntity<ProjectMoney> updateMoney(@PathVariable("id") Long id, @RequestBody ProjectMoney projectMoney){
        return projectMoneyService.updateMoney(id, projectMoney);
    }
}
