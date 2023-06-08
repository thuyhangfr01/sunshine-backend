package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectArtifact;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectArtifactService {
    //get artifaces by project id
    public ResponseEntity<List<ProjectArtifact>> getArtifact(Long projectId);

    //add artiface by project id
    public ResponseEntity<ProjectArtifact> addArtifact(Long projectId, ProjectArtifact projectArtifact);

    //update artiface by project id
    public ResponseEntity<ProjectArtifact> updateArtifact(Long id, ProjectArtifact projectArtifact);

    //delete all artifaces by project id
    public ResponseEntity<Boolean> deleteAllArtifact(Long projectId);

    //delete artiface by id
    public ResponseEntity<Boolean> deleteArtifact(Long id);
}
