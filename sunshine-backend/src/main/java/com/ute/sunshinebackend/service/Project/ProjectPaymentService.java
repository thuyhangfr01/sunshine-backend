package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.dto.ProjectDto.ProjectPaymentCreatorDto;
import com.ute.sunshinebackend.dto.ProjectDto.ProjectPaymentDto;
import com.ute.sunshinebackend.dto.ProjectDto.SumMoneyDto;
import com.ute.sunshinebackend.dto.ProjectDto.UnionDto;
import org.springframework.http.ResponseEntity;

import java.util.*;

public interface ProjectPaymentService {
    public ResponseEntity<List<ProjectPaymentDto>> getAllPaymentByProjectId(long projectId);
//    public ResponseEntity<ProjectPaymentDto> getProjectPaymentById(String id);
    public ResponseEntity<ProjectPaymentCreatorDto> createProjectPayment(ProjectPaymentCreatorDto projectPaymentCreatorDto);
    public ResponseEntity<List<UnionDto>> unionByDate(String fromDate1, String toDate1, String fromDate2, String toDate2);
    public ResponseEntity<List<UnionDto>> unionByProjectIdByDate(Integer projectId1, Integer projectId2, String fromDate1, String toDate1, String fromDate2, String toDate2);
    public ResponseEntity<List<UnionDto>> union();
}
