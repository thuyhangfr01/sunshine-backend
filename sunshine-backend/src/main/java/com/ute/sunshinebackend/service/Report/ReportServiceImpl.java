package com.ute.sunshinebackend.service.Report;

import com.ute.sunshinebackend.dto.ProjectDto.UnionDto;
import com.ute.sunshinebackend.dto.Report.ContributionReportDto;
import com.ute.sunshinebackend.dto.Report.PaymentReportDto;
import com.ute.sunshinebackend.repository.Report.ContributionReportRepository;
import com.ute.sunshinebackend.repository.Report.PaymentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    ContributionReportRepository contributionReportRepository;

    @Autowired
    PaymentReportRepository paymentReportRepository;

    @Override
    public ResponseEntity<List<ContributionReportDto>> listContributionsByProjectIdByDate(Integer projectId, String fromDate, String toDate) {
        List<ContributionReportDto> contributionReportDtos = new ArrayList<>();
        if(projectId == 0 && (fromDate.length() == 1 || toDate.length() == 1)) {
            List<Object []> result = contributionReportRepository.listContributions();

            for(Object[] row : result){
                ContributionReportDto contributionReportDto = new ContributionReportDto();
                contributionReportDto.setId((String) row[0]);
                contributionReportDto.setAmountMoney((BigDecimal) row[1]);
                contributionReportDto.setUserName((String) row[2]);
                contributionReportDto.setType((String) row[3]);
                contributionReportDto.setCreatedAt((Date) row[4]);
                contributionReportDto.setReceiver((String) row[5]);
                contributionReportDto.setProjectId((Integer) row[6]);

                contributionReportDtos.add(contributionReportDto);
            }
        }
        else if(projectId == 0 && (fromDate.length() != 1 && toDate.length() != 1)){
            List<Object []> result = contributionReportRepository.listContributionsByDate(fromDate, toDate);

            for(Object[] row : result){
                ContributionReportDto contributionReportDto = new ContributionReportDto();
                contributionReportDto.setId((String) row[0]);
                contributionReportDto.setAmountMoney((BigDecimal) row[1]);
                contributionReportDto.setUserName((String) row[2]);
                contributionReportDto.setType((String) row[3]);
                contributionReportDto.setCreatedAt((Date) row[4]);
                contributionReportDto.setReceiver((String) row[5]);
                contributionReportDto.setProjectId((Integer) row[6]);

                contributionReportDtos.add(contributionReportDto);
            }
        } else if(projectId != 0 && (fromDate.length() == 1|| toDate.length() == 1)){
            List<Object []> result = contributionReportRepository.listContributionsByProjectId(projectId);

            for(Object[] row : result){
                ContributionReportDto contributionReportDto = new ContributionReportDto();
                contributionReportDto.setId((String) row[0]);
                contributionReportDto.setAmountMoney((BigDecimal) row[1]);
                contributionReportDto.setUserName((String) row[2]);
                contributionReportDto.setType((String) row[3]);
                contributionReportDto.setCreatedAt((Date) row[4]);
                contributionReportDto.setReceiver((String) row[5]);
                contributionReportDto.setProjectId((Integer) row[6]);

                contributionReportDtos.add(contributionReportDto);
            }
        } else if(projectId != 0 && (fromDate.length() != 1 && toDate.length() != 1)){
            List<Object []> result = contributionReportRepository.listContributionsByProjectIdByDate(projectId, fromDate, toDate);

            for(Object[] row : result){
                ContributionReportDto contributionReportDto = new ContributionReportDto();
                contributionReportDto.setId((String) row[0]);
                contributionReportDto.setAmountMoney((BigDecimal) row[1]);
                contributionReportDto.setUserName((String) row[2]);
                contributionReportDto.setType((String) row[3]);
                contributionReportDto.setCreatedAt((Date) row[4]);
                contributionReportDto.setReceiver((String) row[5]);
                contributionReportDto.setProjectId((Integer) row[6]);

                contributionReportDtos.add(contributionReportDto);
            }
        }
        return new ResponseEntity<>(contributionReportDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PaymentReportDto>> listPaymentsByProjectIdByDate(Integer projectId, String fromDate, String toDate) {
        List<PaymentReportDto> paymentReportDtos = new ArrayList<>();
        if(projectId == 0 && (fromDate.length() == 1 || toDate.length() == 1)) {
            List<Object []> result = paymentReportRepository.listPayments();

            for(Object[] row : result){
                PaymentReportDto paymentReportDto = new PaymentReportDto();
                paymentReportDto.setId((String) row[0]);
                paymentReportDto.setAmountMoney((BigDecimal) row[1]);
                paymentReportDto.setUserName((String) row[2]);
                paymentReportDto.setType((String) row[3]);
                paymentReportDto.setCreatedAt((Date) row[4]);
                paymentReportDto.setReceiver((String) row[5]);
                paymentReportDto.setProjectId((Integer) row[6]);

                paymentReportDtos.add(paymentReportDto);
            }
        }
        else if(projectId == 0 && (fromDate.length() != 1 && toDate.length() != 1)){
            List<Object []> result = paymentReportRepository.listPaymentsByDate(fromDate, toDate);

            for(Object[] row : result){
                PaymentReportDto paymentReportDto = new PaymentReportDto();
                paymentReportDto.setId((String) row[0]);
                paymentReportDto.setAmountMoney((BigDecimal) row[1]);
                paymentReportDto.setUserName((String) row[2]);
                paymentReportDto.setType((String) row[3]);
                paymentReportDto.setCreatedAt((Date) row[4]);
                paymentReportDto.setReceiver((String) row[5]);
                paymentReportDto.setProjectId((Integer) row[6]);

                paymentReportDtos.add(paymentReportDto);
            }
        } else if(projectId != 0 && (fromDate.length() == 1|| toDate.length() == 1)){
            List<Object []> result = paymentReportRepository.listPaymentsByProjectId(projectId);

            for(Object[] row : result){
                PaymentReportDto paymentReportDto = new PaymentReportDto();
                paymentReportDto.setId((String) row[0]);
                paymentReportDto.setAmountMoney((BigDecimal) row[1]);
                paymentReportDto.setUserName((String) row[2]);
                paymentReportDto.setType((String) row[3]);
                paymentReportDto.setCreatedAt((Date) row[4]);
                paymentReportDto.setReceiver((String) row[5]);
                paymentReportDto.setProjectId((Integer) row[6]);

                paymentReportDtos.add(paymentReportDto);
            }
        } else if(projectId != 0 && (fromDate.length() != 1 && toDate.length() != 1)){
            List<Object []> result = paymentReportRepository.listPaymentsByProjectIdByDate(projectId, fromDate, toDate);

            for(Object[] row : result){
                PaymentReportDto paymentReportDto = new PaymentReportDto();
                paymentReportDto.setId((String) row[0]);
                paymentReportDto.setAmountMoney((BigDecimal) row[1]);
                paymentReportDto.setUserName((String) row[2]);
                paymentReportDto.setType((String) row[3]);
                paymentReportDto.setCreatedAt((Date) row[4]);
                paymentReportDto.setReceiver((String) row[5]);
                paymentReportDto.setProjectId((Integer) row[6]);

                paymentReportDtos.add(paymentReportDto);
            }
        }
        return new ResponseEntity<>(paymentReportDtos, HttpStatus.OK);
    }
}
