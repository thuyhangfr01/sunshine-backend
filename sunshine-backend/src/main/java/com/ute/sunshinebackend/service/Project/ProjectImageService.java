package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectImage;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface ProjectImageService {
    //get all images by project id
    public ResponseEntity<List<ProjectImage>> getAllImagesByIdProject(Long idProject);

    //add image of project id
    public ResponseEntity<ProjectImage> addImage(Long idProject, ProjectImage projectImage);

    //delete all images by project id
    public ResponseEntity<Boolean> deleteAllImages(Long idProject);

    //delete image by project id
    public ResponseEntity<Boolean> deteleImageById(Long id);
}
