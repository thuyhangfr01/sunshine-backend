package com.ute.sunshinebackend.service.Project;

import java.util.List;
import com.ute.sunshinebackend.entity.Project.ProjectType;
import org.springframework.http.ResponseEntity;

public interface ProjectTypeService {
    public ResponseEntity<ProjectType> addType(ProjectType projectType);

    public ResponseEntity<ProjectType> updateType(Long id, ProjectType projectType);

    public ResponseEntity<List<ProjectType>> getAllType();

    public ResponseEntity<ProjectType> getTypeById(Long id);
}
