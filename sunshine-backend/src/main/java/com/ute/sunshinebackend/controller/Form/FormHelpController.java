package com.ute.sunshinebackend.controller.Form;

import com.ute.sunshinebackend.dto.FormDto.FormHelpCreatorDto;
import com.ute.sunshinebackend.dto.FormDto.FormHelpDto;
import com.ute.sunshinebackend.entity.Form.FormStatus;
import com.ute.sunshinebackend.service.Form.FormHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FormHelpController {
    @Autowired
    FormHelpService formHelpService;

    @GetMapping("/latestFormHelp")
    public ResponseEntity<List<FormHelpDto>> getLatestFormHelp(){
        return formHelpService.getLatestFormHelp();
    }
//
//    @GetMapping("/formHelp/{id}")
//    public ResponseEntity<FormHelpDto> getFormHelpById(@PathVariable("id") long formId){
//        return formHelpService.getFormHelpById(formId);
//    }
//
//    @GetMapping("/formHelp/user/{id}")
//    public ResponseEntity<List<FormHelpDto>> getFormHelpByUserId(@PathVariable("id") long userId){
//        return formHelpService.getLatestFormHelpByUserId(userId);
//    }

    @PostMapping("/formHelp")
    public ResponseEntity<FormHelpCreatorDto> addFormHelp(@RequestBody FormHelpCreatorDto formHelpCreatorDto){
        return formHelpService.addFormHelp(formHelpCreatorDto);
    }

//    @PutMapping("/formHelp/{id}")
//    public ResponseEntity<FormHelpCreatorDto> updateFormHelp(@PathVariable("id") Long formId, @RequestBody FormHelpCreatorDto formHelpCreatorDto){
//        return formHelpService.updateFormHelpById(formId, formHelpCreatorDto);
//    }

    @PutMapping("/formHelp/status/{id}")
    public ResponseEntity<FormHelpCreatorDto> updateStatusFormById(@PathVariable("id") Long formId, @RequestBody FormHelpCreatorDto formHelpCreatorDto){
        return formHelpService.updateFormHelpStatus(formId, formHelpCreatorDto);
    }
//
//    @DeleteMapping("/formHelp/{id}")
//    public ResponseEntity<Boolean> deleteFormHelpById(@PathVariable("id") Long formId){
//        return formHelpService.deleteFormHelpById(formId);
//    }
}
