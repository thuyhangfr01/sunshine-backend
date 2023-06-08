package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.dto.ProjectDto.ProjectPaymentCreatorDto;
import com.ute.sunshinebackend.dto.ProjectDto.ProjectPaymentDto;
import com.ute.sunshinebackend.dto.ProjectDto.UnionDto;
import com.ute.sunshinebackend.service.Project.ProjectPaymentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjectPaymentController {
    @Autowired
    ProjectPaymentService projectPaymentService;

    @GetMapping("/unionByDate")
    public ResponseEntity<List<UnionDto>> unionByDate(@RequestParam(required = true) String fromDate1,
                                                      @RequestParam(required = true) String toDate1,
                                                      @RequestParam(required = true) String fromDate2,
                                                      @RequestParam(required = true) String toDate2){
        return projectPaymentService.unionByDate(fromDate1, toDate1, fromDate2, toDate2);
    }

    @GetMapping("/unionByProjectIdByDate")
    public ResponseEntity<List<UnionDto>> unionByProjectId(@RequestParam(required = false) Integer projectId1,
                                                           @RequestParam(required = false) Integer projectId2,
                                                           @RequestParam(required = false) String fromDate1,
                                                           @RequestParam(required = false) String toDate1,
                                                           @RequestParam(required = false) String fromDate2,
                                                           @RequestParam(required = false) String toDate2){
        return projectPaymentService.unionByProjectIdByDate(projectId1, projectId2, fromDate1, toDate1, fromDate2, toDate2);
    }

    @GetMapping("/project/{id}/payment")
    public ResponseEntity<List<ProjectPaymentDto>> getAllByProjectId(@PathVariable("id") Long projectId){
        return projectPaymentService.getAllPaymentByProjectId(projectId);
    }

//    @GetMapping("/project/payment/{id}")
//    public ResponseEntity<ProjectPaymentDto> getProjectPaymentById(@PathVariable("id") String id){
//        return projectPaymentService.getProjectPaymentById(id);
//    }

    @PostMapping("/project/payment")
    public ResponseEntity<ProjectPaymentCreatorDto> createProjectPayment(@RequestBody ProjectPaymentCreatorDto projectPaymentCreatorDto){
        return projectPaymentService.createProjectPayment(projectPaymentCreatorDto);
    }

}
