package com.ute.sunshinebackend.service.Report;

import com.ute.sunshinebackend.dto.Report.ContributionReportDto;
import com.ute.sunshinebackend.dto.Report.ContributionUserArtifactDto;
import com.ute.sunshinebackend.dto.Report.ContributionUserDto;
import com.ute.sunshinebackend.dto.Report.PaymentReportDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportService {
    public ResponseEntity<List<ContributionUserDto>> listContributionsByUserId(Integer userId);
    public ResponseEntity<List<ContributionUserArtifactDto>> listContributionArtifactsByUserId(Integer userId);
    public ResponseEntity<List<ContributionReportDto>> listContributionsByProjectIdByDate(Integer projectId, String fromDate, String toDate);
    public ResponseEntity<List<PaymentReportDto>> listPaymentsByProjectIdByDate(Integer projectId, String fromDate, String toDate);
}
