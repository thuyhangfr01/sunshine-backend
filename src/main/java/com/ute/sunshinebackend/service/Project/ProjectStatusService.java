package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectStatusService {
    public ResponseEntity<List<ProjectStatus>> getAllStatus();
}
