package com.ute.sunshinebackend.controller;

import com.ute.sunshinebackend.entity.Report;
import com.ute.sunshinebackend.service.ReportService;
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

    @GetMapping("/reports")
    public ResponseEntity<List<Report>> getAllList(){
        return reportService.getAllFiles();
    }

    @GetMapping("/reports/title")
    public ResponseEntity<List<Report>> getListsByTitle(@RequestParam("title") String title){
        return reportService.getFilesByTitle(title);
    }

    @PostMapping("/report")
    public ResponseEntity<Report> addFile(Report report){
        return reportService.addFile(report);
    }
}
