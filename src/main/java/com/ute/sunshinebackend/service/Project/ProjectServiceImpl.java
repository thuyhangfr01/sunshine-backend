package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.dto.ProjectDto.ProjectCreatorDto;
import com.ute.sunshinebackend.dto.ProjectDto.ProjectCustomListDto;
import com.ute.sunshinebackend.dto.ProjectDto.ProjectNameDto;
import com.ute.sunshinebackend.dto.ContributionDto.TotalMoneyDto;
import com.ute.sunshinebackend.dto.Report.ContributionUserDto;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Project.ProjectMoneyRepository;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import com.ute.sunshinebackend.repository.Project.ProjectStatusRepository;
import com.ute.sunshinebackend.repository.Project.ProjectTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    ProjectMoneyRepository projectMoneyRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Cacheable(value = "myCache")
    @Override
    public ResponseEntity<List<ProjectNameDto>> getListProjectName() {
        List<Project> project = projectRepository.findAll();
        List<ProjectNameDto> projectNameDto = new ArrayList<ProjectNameDto>();

        for(int i = 0; i < project.size(); i++){
            ProjectNameDto proNameDto = new ProjectNameDto(
                    project.get(i).getId(),
                    project.get(i).getName()
            );
            projectNameDto.add(proNameDto);
        }
        return new ResponseEntity<>(projectNameDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Project> getProjectById(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);

        return new ResponseEntity<>(project.get(), HttpStatus.OK);
    }
    @Cacheable(value = "myCache")
    @Override
    public ResponseEntity<Map<String, Object>> getAll(String name, int page, int size) {
        try {
            List<Project> projects = new ArrayList<Project>();
            Pageable paging = PageRequest.of(page, size);

            Page<Project> pageProjs;
            if (name == null)
                pageProjs = projectRepository.findByOrderByCreatedAtDesc(paging);
            else
                pageProjs = projectRepository.findByNameContainingOrderByCreatedAtDesc(name, paging);

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
    @Cacheable(value = "myCache")
    @Override
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @Cacheable(value = "myCache")
    @Override
    public ResponseEntity<Map<String, Object>> getProjectsByTypeId(Long idType, int page, int size) {
        try {
            List<Project> projects = new ArrayList<Project>();
            Pageable paging = PageRequest.of(page, size);

            Page<Project> pageProjs;
            pageProjs = projectRepository.findByProjectTypeIdOrderByCreatedAtDesc(idType, paging);

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
    @Cacheable(value = "myCache")
    @Override
    public ResponseEntity<Map<String, Object>> getProjectsByStatusId(Long idStatus, int page, int size) {
        try {
            List<Project> projects = new ArrayList<Project>();
            Pageable paging = PageRequest.of(page, size);

            Page<Project> pageProjs;
            pageProjs = projectRepository.findByProjectStatusIdOrderByCreatedAtDesc(idStatus, paging);

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
    @Cacheable(value = "myCache")
    @Override
    public ResponseEntity<Page<Project>> getTop5LatestProject(Pageable pageable) {
        Page<Project> projects = projectRepository.findByOrderByCreatedAtDesc(pageable);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @Cacheable(value = "myCache")
    @Override
    public ResponseEntity<List<Project>> getLatestProject(String name) {

            List<Project> projects = new ArrayList<Project>();

            if (name == null)
                projectRepository.findByOrderByCreatedAtDesc().forEach(projects::add);
            else
                projectRepository.findByNameContainingOrderByCreatedAtDesc(name).forEach(projects::add);

            return new ResponseEntity<>(projects, HttpStatus.OK);

    }

    @CacheEvict(value = "myCache", allEntries = true)
    @Override
    public ResponseEntity<ProjectCreatorDto> addProject(ProjectCreatorDto projectCreatorDtoRequest) {
        try{
            //convert dto to entity
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            Project projectRequest = modelMapper.map(projectCreatorDtoRequest, Project.class);

            projectTypeRepository.findById(projectCreatorDtoRequest.getTypeId()).map(projectType -> {
                projectRequest.setProjectType(projectType);

                return projectRequest;
            }).orElseThrow(() -> new ResourceNotFoundException("Not found Project type with id"));

            projectStatusRepository.findById(projectCreatorDtoRequest.getStatusId()).map(projectStatus -> {
                projectRequest.setProjectStatus(projectStatus);

                return projectRequest;
            }).orElseThrow(() -> new ResourceNotFoundException("Not status type with id"));

            //convert entity to dto
            ProjectCreatorDto projectCreatorDto = modelMapper.map(projectRepository.save(projectRequest), ProjectCreatorDto.class);

            return new ResponseEntity<>(projectCreatorDto, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CacheEvict(value = "myCache", allEntries = true)
    @Override
    public ResponseEntity<ProjectCreatorDto> updateProject(Long id, ProjectCreatorDto projectCreatorDto) {
        try{
            //convert dto to entity
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            Project projectRequest = modelMapper.map(projectCreatorDto, Project.class);

            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Project id " + id + "not found"));

            projectTypeRepository.findById(projectCreatorDto.getTypeId()).map(projectType -> {
            projectRequest.setProjectType(projectType);

                return projectRequest;
            }).orElseThrow(() -> new ResourceNotFoundException("Not found type with id"));

            projectStatusRepository.findById(projectCreatorDto.getStatusId()).map(projectStatus -> {
                projectRequest.setProjectStatus(projectStatus);

                return projectRequest;
            }).orElseThrow(() -> new ResourceNotFoundException("Not status with id"));

            project.setName(projectRequest.getName());
            project.setDetails(projectRequest.getDetails());
            project.setNumVolunteers(projectRequest.getNumVolunteers());
            project.setPosition(projectRequest.getPosition());
            project.setStartTime(projectRequest.getStartTime());
            project.setEndTime(projectRequest.getEndTime());
            project.setHoldTime(projectRequest.getHoldTime());
            project.setProjectType(projectRequest.getProjectType());
            project.setProjectStatus(projectRequest.getProjectStatus());
            project.setUpdatedAt(projectRequest.getUpdatedAt());
            //convert entity to dto
            ProjectCreatorDto projectCreatorDto1 = modelMapper.map(projectRepository.save(project), ProjectCreatorDto.class);
            return new ResponseEntity<>(projectCreatorDto1, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(value = "myCache", allEntries = true)
    @Override
    public ResponseEntity<Boolean> deleteProject(Long id) {
        projectRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TotalMoneyDto> getTotalMoneyByProjectId(Long projectId) {
            TotalMoneyDto totalMoneyDto = new TotalMoneyDto();
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFoundException("Project id " + projectId + "not found"));

         if (projectRepository.getTotalMoneyByProjectId(project.getId()) == null){
            totalMoneyDto.setTotalMoney(BigDecimal.valueOf(0));
        } else {
            totalMoneyDto.setTotalMoney(projectRepository.getTotalMoneyByProjectId(project.getId()));
        }
        return new ResponseEntity<>(totalMoneyDto, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<ProjectCustomListDto>> getAllProject() {
        List<ProjectCustomListDto> projectCustomListDtos = new ArrayList<>();
        List<Object []> result = projectRepository.getAllProject();

        for(Object[] row : result){
            ProjectCustomListDto projectCustomListDto = new ProjectCustomListDto();
            projectCustomListDto.setId((Integer) row[0]);
            projectCustomListDto.setName((String) row[1]);
            projectCustomListDto.setDetails((String) row[2]);
            projectCustomListDto.setMinMoney((BigDecimal) row[3]);
            projectCustomListDto.setCreatedAt((Date) row[4]);

//            BigDecimal revMoney = projectRepository.getTotalMoneyByProjectId((Integer) row[0]);
//            BigDecimal minMoney = (BigDecimal) row[3];
//            projectCustomListDto.setRevMoney(revMoney);
//
//            List imagesList = projectRepository.getImagesByProjectId((Integer) row[0]);
//            projectCustomListDto.setImagesList(imagesList);

            projectCustomListDtos.add(projectCustomListDto);
        }
        return new ResponseEntity<>(projectCustomListDtos, HttpStatus.OK);
    }
}
