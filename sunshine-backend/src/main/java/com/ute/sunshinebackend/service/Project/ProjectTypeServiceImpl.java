package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectType;
import com.ute.sunshinebackend.repository.Project.ProjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {
    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @Override
    public ResponseEntity<ProjectType> addType(ProjectType projectType) {
        try{
            ProjectType _projectType = projectTypeRepository.save(new ProjectType(projectType.getName()));
            return new ResponseEntity<>(_projectType, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ProjectType> updateType(Long id, ProjectType projectType) {
        if (projectType!=null){
            Optional<ProjectType> data = projectTypeRepository.findById(id);
            if (data.isPresent()){
                ProjectType _projectType = data.get();
                _projectType.setName(projectType.getName());

                return new ResponseEntity<>(projectTypeRepository.save(_projectType), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    public ResponseEntity<List<ProjectType>> getAllType() {
        try{
            List<ProjectType> projectTypes = new ArrayList<ProjectType>();
            projectTypeRepository.findAll().forEach(projectTypes::add);
            if(projectTypes.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projectTypes, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ProjectType> getTypeById(Long id) {
        try{
            Optional<ProjectType> data = projectTypeRepository.findById(id);
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
