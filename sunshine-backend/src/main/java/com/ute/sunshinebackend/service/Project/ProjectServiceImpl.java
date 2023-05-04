package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.dto.ProjectCreatorDto;
import com.ute.sunshinebackend.dto.ProjectListDto;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.entity.Project.ProjectMoney;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import com.ute.sunshinebackend.repository.Project.ProjectStatusRepository;
import com.ute.sunshinebackend.repository.Project.ProjectTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @Autowired
    ProjectStatusRepository projectStatusRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<ProjectListDto>> getAllProjects() {
        List<ProjectListDto> list = projectRepository.getAllProjects();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllProjects(String name, int page, int size) {
        try {
            List<ProjectListDto> projects = new ArrayList<ProjectListDto>();
            Pageable paging = PageRequest.of(page, size);

            Page<ProjectListDto> pageProjs;
            if (name == null)
                pageProjs = projectRepository.getAllProjects(paging);
            else
                pageProjs = projectRepository.findByNameContaining(name, paging);

            projects = pageProjs.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("projects", projects);
            response.put("currentPage", pageProjs.getNumber());
            response.put("totalItems", pageProjs.getTotalElements());
            response.put("totalPages", pageProjs.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getProjectsByTypeId(Long idType, int page, int size) {
        try {
            List<ProjectListDto> projects = new ArrayList<ProjectListDto>();
            Pageable paging = PageRequest.of(page, size);

            Page<ProjectListDto> pageProjs;
            pageProjs = projectRepository.findByProjectTypeId(idType, paging);

            projects = pageProjs.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("projects", projects);
            response.put("currentPage", pageProjs.getNumber());
            response.put("totalItems", pageProjs.getTotalElements());
            response.put("totalPages", pageProjs.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getProjectsByStatusId(Long idStatus, int page, int size) {
        try {
            List<ProjectListDto> projects = new ArrayList<ProjectListDto>();
            Pageable paging = PageRequest.of(page, size);

            Page<ProjectListDto> pageProjs;
            pageProjs = projectRepository.findByProjectStatusId(idStatus, paging);

            projects = pageProjs.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("projects", projects);
            response.put("currentPage", pageProjs.getNumber());
            response.put("totalItems", pageProjs.getTotalElements());
            response.put("totalPages", pageProjs.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<ProjectListDto>> getProjectsByTypeId(Long typeId) {
        if (!projectTypeRepository.existsById(typeId)) {
            throw new ResourceNotFoundException("Not found type with id = " + typeId);
        }

        List<ProjectListDto> projects = projectRepository.findByProjectTypeId(typeId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProjectListDto>> getProjectsByStatusId(Long statusId) {
        if (!projectTypeRepository.existsById(statusId)) {
            throw new ResourceNotFoundException("Not found status with id = " + statusId);
        }

        List<ProjectListDto> projects = projectRepository.findByProjectStatusId(statusId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<ProjectListDto>> getTop5LatestProjects(Pageable pageable) {
        Page<ProjectListDto> projects = projectRepository.findTop5LatestProjects(pageable);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectListDto> getProjectById(Long projectId) {
        Optional<ProjectListDto> project = projectRepository.getProjectById(projectId);

        return new ResponseEntity<>(project.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectCreatorDto> addProject(Long idType, ProjectCreatorDto projectCreatorDtoRequest) {
        //convert dto to entity
        Project projectRequest = modelMapper.map(projectCreatorDtoRequest, Project.class);

        Project project = projectTypeRepository.findById(idType).map(projectType -> {
            projectRequest.setProjectType(projectType);

            return projectRepository.save(projectRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Project type with id = " + idType));

        //convert entity to dto
        ProjectCreatorDto projectCreatorDto = modelMapper.map(project, ProjectCreatorDto.class);

        return new ResponseEntity<>(projectCreatorDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProjectCreatorDto> updateProject(Long id, ProjectCreatorDto projectCreatorDto) {
        //convert dto to entity
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Project projectRequest = modelMapper.map(projectCreatorDto, Project.class);

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project id " + id + "not found"));

        project.setName(projectRequest.getName());
        project.setDetails(projectRequest.getDetails());
        project.setNumVolunteers(projectRequest.getNumVolunteers());
        project.setPosition(projectRequest.getPosition());
        project.setStartTime(projectRequest.getStartTime());
        project.setEndTime(projectRequest.getEndTime());
        project.setHoldTime(projectRequest.getHoldTime());

        Project project1 = projectTypeRepository.findById(projectCreatorDto.getTypeId()).map(projectType -> {
            projectRequest.setProjectType(projectType);

            return projectRepository.save(projectRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found type with id"));

        Project project2 = projectStatusRepository.findById(projectCreatorDto.getStatusId()).map(projectStatus -> {
            projectRequest.setProjectStatus(projectStatus);

            return projectRepository.save(projectRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not status type with id"));

        project.setProjectType(project1.getProjectType());
        project.setProjectStatus(project2.getProjectStatus());
        //convert entity to dto
        ProjectCreatorDto projectCreatorDto1 = modelMapper.map(projectRepository.save(project), ProjectCreatorDto.class);
        return new ResponseEntity<>(projectCreatorDto1, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Boolean> deleteProject(Long id) {
        projectRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
