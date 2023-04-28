package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.entity.Project.ProjectArtifact;
import com.ute.sunshinebackend.service.Project.ProjectArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjectArtifactController {
    @Autowired
    ProjectArtifactService projectArtifactService;

    @GetMapping("/project/{id}/artifacts")
    public ResponseEntity<List<ProjectArtifact>> getArtifact(@PathVariable("id") Long projectId){
        return  projectArtifactService.getArtifact(projectId);
    }

    @PostMapping("/project/{id}/artifact")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<ProjectArtifact> addArtifact(@PathVariable("id") Long projectId, @RequestBody ProjectArtifact projectArtifact){
        return projectArtifactService.addArtifact(projectId, projectArtifact);
    }

    @PutMapping("/project/artifact/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<ProjectArtifact> updateArtifact(@PathVariable("id") Long id, @RequestBody ProjectArtifact projectArtifact){
        return projectArtifactService.updateArtifact(id, projectArtifact);
    }

    @DeleteMapping("/project/{id}/artifacts")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<Boolean> deleteAllArtifacts(@PathVariable("id") Long projectId){
        return projectArtifactService.deleteAllArtifact(projectId);
    }

    @DeleteMapping("/project/artifact/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<Boolean> deleteArtifact(@PathVariable("id") Long id){
        return projectArtifactService.deleteArtifact(id);
    }
}
