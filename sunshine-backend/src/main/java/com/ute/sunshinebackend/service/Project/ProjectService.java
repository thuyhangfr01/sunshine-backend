package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.dto.ProjectCreatorDto;
import com.ute.sunshinebackend.dto.ProjectListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface ProjectService {
    //get all projects
    public ResponseEntity<List<ProjectListDto>> getAllProjects();

    //get all projects by typeId
    public ResponseEntity<List<ProjectListDto>> getProjectsByTypeId(Long typeId);

    //get all projects by statusId
    public ResponseEntity<List<ProjectListDto>> getProjectsByStatusId(Long statusId);

    //get top 5 projects by created_at
    public ResponseEntity<Page<ProjectListDto>> getTop5LatestProjects(Pageable pageable);

    //get project by id
    public ResponseEntity<ProjectListDto> getProjectById(Long id);

    //add project by type id
    public ResponseEntity<ProjectCreatorDto> addProject(Long idType, ProjectCreatorDto projectCreatorDtoRequest);

    //update project by id
    public ResponseEntity<ProjectCreatorDto> updateProject(Long id, ProjectCreatorDto projectCreatorDto);

    //delete project by id
    public ResponseEntity<Boolean> deleteProject(Long id);

    //pagination - sorting
    public ResponseEntity<Map<String, Object>> getAllProjects(String name, int page, int size);
    public ResponseEntity<Map<String, Object>> getProjectsByTypeId(Long idType, int page, int size);

    public ResponseEntity<Map<String, Object>> getProjectsByStatusId(Long idStatus, int page, int size);
}
