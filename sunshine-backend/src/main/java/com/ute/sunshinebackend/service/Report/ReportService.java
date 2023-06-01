package com.ute.sunshinebackend.service.Report;

import com.ute.sunshinebackend.dto.Report.ContributionReportDto;
import com.ute.sunshinebackend.dto.Report.PaymentReportDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportService {
    public ResponseEntity<List<ContributionReportDto>> listContributionsByProjectIdByDate(Integer projectId, String fromDate, String toDate);
    public ResponseEntity<List<PaymentReportDto>> listPaymentsByProjectIdByDate(Integer projectId, String fromDate, String toDate);
}
