package com.ute.sunshinebackend.repository.Project;

import com.ute.sunshinebackend.dto.ProjectTypeDto;
import com.ute.sunshinebackend.entity.Project.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long> {
//    List<ProjectTypeDto> findAll();
}
