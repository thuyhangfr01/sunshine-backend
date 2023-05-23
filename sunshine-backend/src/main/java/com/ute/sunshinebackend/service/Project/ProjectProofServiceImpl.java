package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectProof;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Project.ProjectProofRepository;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectProofServiceImpl implements ProjectProofService{
    @Autowired
    ProjectProofRepository projectProofRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public ResponseEntity<List<ProjectProof>> getAllProofsByIdProject(Long idProject) {
        if (!projectRepository.existsById(idProject)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + idProject);
        }

        List<ProjectProof> projectProofs = projectProofRepository.findByProjectId(idProject);
        return new ResponseEntity<>(projectProofs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectProof> addProof(Long idProject, ProjectProof projectProof) {
        try {
            ProjectProof projectProof1 = projectRepository.findById(idProject).map(project -> {
                projectProof.setProject(project);
                return projectProofRepository.save(projectProof);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found project wwith id = " + idProject));

            return new ResponseEntity<>(projectProof1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteAllProofs(Long idProject) {
        if (!projectRepository.existsById(idProject)) {
            throw new ResourceNotFoundException("Not found project with id = " + idProject);
        }

        projectProofRepository.deleteByProjectId(idProject);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Boolean> deteleProofById(Long id) {
        projectProofRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
