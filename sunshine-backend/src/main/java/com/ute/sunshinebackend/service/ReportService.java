package com.ute.sunshinebackend.service;

import com.ute.sunshinebackend.entity.Report;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ReportService {
    public ResponseEntity<List<Report>> getAllFiles();
    public ResponseEntity<List<Report>> getFilesByTitle(String title);
    public ResponseEntity<Report> addFile(Report report);
}
