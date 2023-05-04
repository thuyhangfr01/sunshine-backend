package com.ute.sunshinebackend.repository.Project;

import com.ute.sunshinebackend.dto.ProjectListDto;
import com.ute.sunshinebackend.entity.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c WHERE b.id = :idType")
    List<ProjectListDto> findByProjectTypeId(Long idType);

    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c WHERE c.id = :idStatus")
    List<ProjectListDto> findByProjectStatusId(Long idStatus);

    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c ")
    List<ProjectListDto> getAllProjects();

    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c WHERE a.id = :id ")
    Optional<ProjectListDto> getProjectById(Long id);

    boolean existsById(Long idProject);

    @Query(value = "SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
            + "FROM Project a JOIN a.projectType b JOIN a.projectStatus c ORDER BY a.createdAt DESC")
    Page<ProjectListDto> findTop5LatestProjects(Pageable pageable);

    Optional<Project> findById(Long projectId);

    @Transactional
    void deleteById(Long id);


    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c")
    Page<ProjectListDto> getAllProjects(Pageable pageable);
    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c WHERE b.id = :idType")
    Page<ProjectListDto> findByProjectTypeId(Long idType, Pageable pageable);
    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c WHERE c.id = :idStatus")
    Page<ProjectListDto> findByProjectStatusId(Long idStatus, Pageable pageable);

    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c WHERE a.name LIKE %:name%")
    Page<ProjectListDto> findByNameContaining(String name, Pageable pageable);

}
