package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.entity.Project.ProjectImage;
import com.ute.sunshinebackend.entity.Project.ProjectProof;
import com.ute.sunshinebackend.service.Project.ProjectProofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
@RequestMapping("/api")
public class ProjectProofController {
    @Autowired
    ProjectProofService proofService;

    @GetMapping("/project/{id}/proofs")
    public ResponseEntity<List<ProjectProof>> getAllProofs(@PathVariable("id") Long idProject){
        return proofService.getAllProofsByIdProject(idProject);
    }

    @PostMapping("/project/{id}/proof")
    public ResponseEntity<ProjectProof> addProof(@PathVariable("id") Long idProject, @RequestBody ProjectProof projectProof){
        return proofService.addProof(idProject, projectProof);
    }

    @DeleteMapping("/project/{id}/proofs")
    public ResponseEntity<Boolean> deleteAllProofs(@PathVariable("id") Long idProject){
        return proofService.deleteAllProofs(idProject);
    }

    @DeleteMapping("/project/proof/{id}")
    public ResponseEntity<Boolean> deleteProof(@PathVariable("id") Long id){
        return proofService.deteleProofById(id);
    }
}
