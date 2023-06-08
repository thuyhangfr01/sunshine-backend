package com.ute.sunshinebackend.controller;

import com.ute.sunshinebackend.entity.Report;
import com.ute.sunshinebackend.service.ReportOldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ReportOldController {
    @Autowired
    ReportOldService reportOldService;

    @GetMapping("/reports")
    public ResponseEntity<List<Report>> getAllList(){
        return reportOldService.getAllFiles();
    }

    @GetMapping("/reports/title")
    public ResponseEntity<List<Report>> getListsByTitle(@RequestParam("title") String title){
        return reportOldService.getFilesByTitle(title);
    }

    @PostMapping("/report")
    public ResponseEntity<Report> addFile(Report report){
        return reportOldService.addFile(report);
    }
}
