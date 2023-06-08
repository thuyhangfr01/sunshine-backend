package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.entity.Project.ProjectImage;
import com.ute.sunshinebackend.repository.Project.ProjectImageRepository;
import com.ute.sunshinebackend.service.Project.ProjectImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjectImageController {
    @Autowired
    ProjectImageService projectImageService;

    @GetMapping("/project/{id}/images")
    public ResponseEntity<List<ProjectImage>> getAllImages(@PathVariable("id") Long idProject){
        return projectImageService.getAllImagesByIdProject(idProject);
    }

    @PostMapping("/project/{id}/image")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<ProjectImage> addImage(@PathVariable("id") Long idProject, @RequestBody ProjectImage projectImage){
        return projectImageService.addImage(idProject, projectImage);
    }

    @DeleteMapping("/project/{id}/images")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<Boolean> deleteAllImages(@PathVariable("id") Long idProject){
        return projectImageService.deleteAllImages(idProject);
    }

    @DeleteMapping("/project/image/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public ResponseEntity<Boolean> deleteImage(@PathVariable("id") Long id){
        return projectImageService.deteleImageById(id);
    }

}
