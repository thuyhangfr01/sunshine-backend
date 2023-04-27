package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.dto.ProjectDto;
import com.ute.sunshinebackend.dto.ProjectJoinDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ProjectService {
    //get all projects
    public ResponseEntity<List<ProjectJoinDto>> getAllProjects();

    //get all projects by typeId

    //get all projects by statusId

    //get top 5 projects by created_at

    //get project by id

    //add project by type id (list money - list artifacts - images)
    public ResponseEntity<ProjectDto> addProject(Long idType, ProjectDto projectDtoRequest);

    //update project (list money - list artifacts - images - proof - type)

    //delete project

    //search by nameProject - pagination
}
