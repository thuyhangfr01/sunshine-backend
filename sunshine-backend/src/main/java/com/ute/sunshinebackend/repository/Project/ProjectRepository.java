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
    boolean existsById(Long idProject);
    Page<Project> findAll(Pageable pageable);
    Page<Project> findByNameContaining(String name, Pageable pageable);
    Page<Project> findByProjectTypeId(Long idType, Pageable pageable);
    Page<Project> findByProjectStatusId(Long idStatus, Pageable pageable);
    Page<Project> findByOrderByCreatedAtDesc(Pageable pageable);
    List<Project> findByNameContainingOrderByCreatedAtDesc(String name);
    List<Project> findByOrderByCreatedAtDesc();
    @Transactional
    void deleteById(Long id);
//    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
//            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c WHERE b.id = :idType")
//    List<ProjectListDto> findByProjectTypeId(Long idType);

//    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
//            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c WHERE a.id = :id ")
//    Optional<ProjectListDto> getProjectById(Long id);

//    @Query(value = "SELECT new com.ute.sunshinebackend.dto.ProjectListDto(a.id, a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
//            + "FROM Project a JOIN a.projectType b JOIN a.projectStatus c ORDER BY a.createdAt DESC")
//    Page<ProjectListDto> findTop5LatestProjects(Pageable pageable);
}
