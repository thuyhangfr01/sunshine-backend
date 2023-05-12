package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectArtifact;
import com.ute.sunshinebackend.entity.Project.ProjectImage;
import com.ute.sunshinebackend.entity.Project.ProjectMoney;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Project.ProjectMoneyRepository;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectMoneyServiceImpl implements ProjectMoneyService{
    @Autowired
    ProjectMoneyRepository projectMoneyRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public ResponseEntity<List<ProjectMoney>> getMoney(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + projectId);
        }

        List<ProjectMoney> projectMoney = projectMoneyRepository.findByProjectId(projectId);
        return new ResponseEntity<>(projectMoney, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Optional<ProjectMoney>> getMoneyById(Long id) {
        return new ResponseEntity<>(projectMoneyRepository.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectMoney> addMoney(Long projectId, ProjectMoney projectMoney) {
        try {
            ProjectMoney projectMoney1 = projectRepository.findById(projectId).map(project -> {
                projectMoney.setProject(project);
                return projectMoneyRepository.save(projectMoney);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found project with id = " + projectId));

            return new ResponseEntity<>(projectMoney1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ProjectMoney> updateMoney(Long moneyId, ProjectMoney projectMoney) {
        ProjectMoney projectMoney1 = projectMoneyRepository.findById(moneyId)
                .orElseThrow(() -> new ResourceNotFoundException("Project money id " + moneyId + "not found"));

        projectMoney1.setMinMoney(projectMoney.getMinMoney());

        return new ResponseEntity<>(projectMoneyRepository.save(projectMoney1), HttpStatus.OK);
    }
}
