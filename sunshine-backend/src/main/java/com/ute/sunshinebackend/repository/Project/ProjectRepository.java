package com.ute.sunshinebackend.repository.Project;

import com.ute.sunshinebackend.dto.ProjectJoinDto;
import com.ute.sunshinebackend.entity.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByProjectType(Long idType);

    boolean existsById(Long idProject);

    @Query("SELECT new com.ute.sunshinebackend.dto.ProjectJoinDto(a.name, a.details, b.name, a.numVolunteers, c.name, a.position, a.startTime, a.endTime, a.holdTime) "
            + " FROM Project a JOIN a.projectType b JOIN a.projectStatus c ")
    List<ProjectJoinDto> getAllProjects();
}
