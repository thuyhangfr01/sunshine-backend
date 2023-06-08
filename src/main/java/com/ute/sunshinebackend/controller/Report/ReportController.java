package com.ute.sunshinebackend.controller.Report;

import com.ute.sunshinebackend.dto.ProjectDto.UnionDto;
import com.ute.sunshinebackend.dto.Report.ContributionReportDto;
import com.ute.sunshinebackend.dto.Report.ContributionUserArtifactDto;
import com.ute.sunshinebackend.dto.Report.ContributionUserDto;
import com.ute.sunshinebackend.dto.Report.PaymentReportDto;
import com.ute.sunshinebackend.entity.Project.ProjectArtifact;
import com.ute.sunshinebackend.service.Report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/reports/contributions")
    public ResponseEntity<List<ContributionReportDto>> listContributionsByProjectIdByDate(@RequestParam(required = false) Integer projectId,
                                                                                          @RequestParam(required = false) String fromDate,
                                                                                          @RequestParam(required = false) String toDate){
        return reportService.listContributionsByProjectIdByDate(projectId, fromDate, toDate);
    }

    @GetMapping("/reports/contributions/artifacts/user")
    public ResponseEntity<List<ContributionUserArtifactDto>> listContributionArtifactsByUserId(@RequestParam(required = false) Integer userId){
        return reportService.listContributionArtifactsByUserId(userId);
    }

    @GetMapping("/reports/contributions/user")
    public ResponseEntity<List<ContributionUserDto>> listContributionsByUserId(@RequestParam(required = false) Integer userId){
        return reportService.listContributionsByUserId(userId);
    }

    @GetMapping("/reports/payments")
    public ResponseEntity<List<PaymentReportDto>> listPaymentsByProjectIdByDate(@RequestParam(required = false) Integer projectId,
                                                                                @RequestParam(required = false) String fromDate,
                                                                                @RequestParam(required = false) String toDate){
        return reportService.listPaymentsByProjectIdByDate(projectId, fromDate, toDate);
    }

    @GetMapping("/statistic/totalContributions")
    public ResponseEntity<?> totalContributions(){
        return reportService.totalContributions();
    }

    @GetMapping("/statistic/totalProjects")
    public ResponseEntity<?> totalProjects(){
        return reportService.totalProjects();
    }

    @GetMapping("/statistic/totalForms")
    public ResponseEntity<?> totalForms(){
        return reportService.totalForms();
    }

    @GetMapping("/statistic/totalMoney")
    public ResponseEntity<?> totalMoney(){
        return reportService.totalMoney();
    }

}
