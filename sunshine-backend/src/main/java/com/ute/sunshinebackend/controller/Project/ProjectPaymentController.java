package com.ute.sunshinebackend.controller.Project;

import com.ute.sunshinebackend.dto.ProjectDto.ProjectPaymentCreatorDto;
import com.ute.sunshinebackend.dto.ProjectDto.ProjectPaymentDto;
import com.ute.sunshinebackend.dto.ProjectDto.SumMoneyDto;
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

    @GetMapping("/unionByProjectId")
    public ResponseEntity<List<UnionDto>> unionByProjectId(@RequestParam(required = false) Integer projectId1,
                                                           @RequestParam(required = false) Integer projectId2){
        return projectPaymentService.unionByProjectId(projectId1, projectId2);
    }

    @GetMapping("/sumMoneyByContribution")
    public ResponseEntity<SumMoneyDto> sumMoneyByContribution(@RequestParam(required = false) Integer projectId){
        return projectPaymentService.sumMoneyByContribution(projectId);
    }

    @GetMapping("/sumMoneyByContributionByDate")
    public ResponseEntity<SumMoneyDto> sumMoneyByContributionByDate(@RequestParam(required = false) String fromDate, String toDate){
        return projectPaymentService.sumMoneyByContributionByDate(fromDate, toDate);
    }

    @GetMapping("/sumMoneyByPayment")
    public ResponseEntity<SumMoneyDto> sumMoneyByPayment(@RequestParam(required = false) Integer projectId){
        return projectPaymentService.sumMoneyByPayment(projectId);
    }

    @GetMapping("/sumMoneyByPaymentByDate")
    public ResponseEntity<SumMoneyDto> sumMoneyByPaymentByDate(@RequestParam(required = false) String fromDate, String toDate){
        return projectPaymentService.sumMoneyByPaymentByDate(fromDate, toDate);
    }

    @GetMapping("/project/{id}/payment")
    public ResponseEntity<List<ProjectPaymentDto>> getAllByProjectId(@PathVariable("id") Long projectId){
        return projectPaymentService.getAllPaymentByProjectId(projectId);
    }

    @PostMapping("/project/payment")
    public ResponseEntity<ProjectPaymentCreatorDto> createProjectPayment(@RequestBody ProjectPaymentCreatorDto projectPaymentCreatorDto){
        return projectPaymentService.createProjectPayment(projectPaymentCreatorDto);
    }

}
