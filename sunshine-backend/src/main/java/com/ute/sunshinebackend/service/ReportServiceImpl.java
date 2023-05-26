package com.ute.sunshinebackend.service;

import com.ute.sunshinebackend.entity.Report;
import com.ute.sunshinebackend.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    ReportRepository reportRepository;

    @Override
    public ResponseEntity<List<Report>> getAllFiles() {
        return new ResponseEntity<>(reportRepository.findByOrderByCreatedAtDesc(), HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<List<Report>> getFilesByTitle(String title) {
        if(title.length() == 1){
            return new ResponseEntity<>(reportRepository.findByOrderByCreatedAtDesc(), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<>(reportRepository.findByTitle(title), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Report> addFile(Report report) {
        Report _report = new Report();

        _report.setTitle(report.getTitle());
        _report.setNameImg(report.getNameImg());
        _report.setUrlImg(report.getUrlImg());

        return new ResponseEntity<>(reportRepository.save(_report), HttpStatus.CREATED);
    }
}
