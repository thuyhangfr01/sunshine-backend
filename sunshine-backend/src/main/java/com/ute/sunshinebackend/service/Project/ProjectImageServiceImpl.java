package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectImage;
import com.ute.sunshinebackend.entity.User;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Project.ProjectImageRepository;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectImageServiceImpl implements ProjectImageService {
    @Autowired
    ProjectImageRepository projectImageRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public ResponseEntity<List<ProjectImage>> getAllImagesByIdProject(Long idProject) {
        if (!projectRepository.existsById(idProject)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + idProject);
        }

        List<ProjectImage> projectImages = projectImageRepository.findByProjectId(idProject);
        return new ResponseEntity<>(projectImages, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectImage> addImage(Long idProject, ProjectImage projectImage) {
        try {
            ProjectImage projectImage1 = projectRepository.findById(idProject).map(project -> {
                projectImage.setProject(project);
                return projectImageRepository.save(projectImage);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found project wwith id = " + idProject));

            return new ResponseEntity<>(projectImage1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteAllImages(Long idProject) {
        if (!projectRepository.existsById(idProject)) {
            throw new ResourceNotFoundException("Not found project with id = " + idProject);
        }

        projectImageRepository.deleteByProjectId(idProject);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Boolean> deteleImageById(Long id) {
        projectImageRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
