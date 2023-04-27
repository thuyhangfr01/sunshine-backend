package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectProof;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectProofService {
    //get all proofs by project id
    public ResponseEntity<List<ProjectProof>> getAllProofsByIdProject(Long idProject);

    //add proof of project id
    public ResponseEntity<ProjectProof> addProof(Long idProject, ProjectProof projectProof);

    //delete all proofs by project id
    public ResponseEntity<Boolean> deleteAllProofs(Long idProject);

    //delete proof by project id
    public ResponseEntity<Boolean> deteleProofById(Long id);
}
