package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.entity.Project.ProjectMoney;
import com.ute.sunshinebackend.service.Project.ProjectMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjectMoneyController {
    @Autowired
    ProjectMoneyService projectMoneyService;

    @GetMapping("/project/{id}/money")
    public ResponseEntity<List<ProjectMoney>> getMoney(@PathVariable("id") Long projectId){
        return  projectMoneyService.getMoney(projectId);
    }

    @GetMapping("/project/money/{id}")
    public ResponseEntity<Optional<ProjectMoney>> getMoneyById(@PathVariable("id") Long moneyId){
        return  projectMoneyService.getMoneyById(moneyId);
    }

    @PostMapping("/project/{id}/money")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<ProjectMoney> addMoney(@PathVariable("id") Long projectId, @RequestBody ProjectMoney projectMoney){
        return projectMoneyService.addMoney(projectId, projectMoney);
    }

    @PutMapping("/project/money/{moneyId}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<ProjectMoney> updateMoney(@PathVariable("moneyId") Long moneyId, @RequestBody ProjectMoney projectMoney){
        return projectMoneyService.updateMoney(moneyId, projectMoney);
    }
}
