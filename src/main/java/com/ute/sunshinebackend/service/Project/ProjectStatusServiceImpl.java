package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectStatus;
import com.ute.sunshinebackend.repository.Project.ProjectStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectStatusServiceImpl implements ProjectStatusService{
    @Autowired
    ProjectStatusRepository projectStatusRepository;

    @Override
    public ResponseEntity<List<ProjectStatus>> getAllStatus() {
        try{
            List<ProjectStatus> projectStatus = new ArrayList<ProjectStatus>();
            projectStatusRepository.findAll().forEach(projectStatus::add);
            if(projectStatus.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projectStatus, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
