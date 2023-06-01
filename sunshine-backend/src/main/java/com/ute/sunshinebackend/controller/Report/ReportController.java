package com.ute.sunshinebackend.controller.Report;

import com.ute.sunshinebackend.dto.ProjectDto.UnionDto;
import com.ute.sunshinebackend.dto.Report.ContributionReportDto;
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

    @GetMapping("/reports/payments")
    public ResponseEntity<List<PaymentReportDto>> listPaymentsByProjectIdByDate(@RequestParam(required = false) Integer projectId,
                                                                                @RequestParam(required = false) String fromDate,
                                                                                @RequestParam(required = false) String toDate){
        return reportService.listPaymentsByProjectIdByDate(projectId, fromDate, toDate);
    }
}
