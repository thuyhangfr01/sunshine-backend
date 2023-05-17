package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.dto.ProjectCreatorDto;
import com.ute.sunshinebackend.dto.ProjectListDto;
import com.ute.sunshinebackend.dto.ProjectNameDto;
import com.ute.sunshinebackend.dto.TotalMoneyDto;
import com.ute.sunshinebackend.entity.Project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface ProjectService {
    public ResponseEntity<List<ProjectNameDto>> getListProjectName();
    public ResponseEntity<Project> getProjectById(Long id);
    public ResponseEntity<Map<String, Object>> getAll(String name, int page, int size);
    public ResponseEntity<List<Project>> getAllProjects();
    public ResponseEntity<Map<String, Object>> getProjectsByTypeId(Long idType, int page, int size);
    public ResponseEntity<Map<String, Object>> getProjectsByStatusId(Long idStatus, int page, int size);
    public ResponseEntity<Page<Project>> getTop5LatestProject(Pageable pageable);
    public ResponseEntity<List<Project>> getLatestProject(String name);
    public ResponseEntity<ProjectCreatorDto> addProject(ProjectCreatorDto projectCreatorDtoRequest);
    public ResponseEntity<ProjectCreatorDto> updateProject(Long id, ProjectCreatorDto projectCreatorDto);
    public ResponseEntity<Boolean> deleteProject(Long id);
    public ResponseEntity<TotalMoneyDto> getTotalMoneyByProjectId(Long projectId);

//    public ResponseEntity<Page<ProjectListDto>> getTop5LatestProjects(Pageable pageable);
}
