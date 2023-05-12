package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectArtifact;
import com.ute.sunshinebackend.entity.Project.ProjectMoney;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Project.ProjectArtifactRepository;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectArtifactServiceImpl implements ProjectArtifactService{
    @Autowired
    ProjectArtifactRepository projectArtifactRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public ResponseEntity<List<ProjectArtifact>> getArtifact(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + projectId);
        }

        List<ProjectArtifact> projectArtifacts = projectArtifactRepository.findByProjectId(projectId);
        return new ResponseEntity<>(projectArtifacts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectArtifact> addArtifact(Long projectId, ProjectArtifact projectArtifact) {
        try {
            ProjectArtifact projectArtifact1 = projectRepository.findById(projectId).map(project -> {
                projectArtifact.setProject(project);
                return projectArtifactRepository.save(projectArtifact);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found project with id = " + projectId));

            return new ResponseEntity<>(projectArtifact1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ProjectArtifact> updateArtifact(Long id, ProjectArtifact projectArtifact) {
        ProjectArtifact projectArtifact1 = projectArtifactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project artifact id " + id + "not found"));

        projectArtifact1.setArtifactName(projectArtifact.getArtifactName());
        projectArtifact1.setMinQuantity(projectArtifact.getMinQuantity());
        projectArtifact1.setCalculationUnit(projectArtifact.getCalculationUnit());

        return new ResponseEntity<>(projectArtifactRepository.save(projectArtifact1), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteAllArtifact(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Not found project with id = " + projectId);
        }

        projectArtifactRepository.deleteByProjectId(projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteArtifact(Long id) {
        ProjectArtifact projectArtifact1 = projectArtifactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project artifact id " + id + "not found"));

        projectArtifactRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
