package com.ute.sunshinebackend.controller.Form;

import com.ute.sunshinebackend.dto.FormHelpDto;
import com.ute.sunshinebackend.dto.FormVolunteerCreatorDto;
import com.ute.sunshinebackend.dto.FormVolunteerDto;
import com.ute.sunshinebackend.entity.Form.FormStatus;
import com.ute.sunshinebackend.service.Form.FormHelpService;
import com.ute.sunshinebackend.service.Form.FormVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FormVolunteerController {
    @Autowired
    FormVolunteerService formVolunteerService;

    @GetMapping("/latestFormVolunteer")
    public ResponseEntity<List<FormVolunteerDto>> getLatestFormVolunteer(){
        return formVolunteerService.getLatestFormVolunteer();
    }

    @GetMapping("/formVolunteer/{id}")
    public ResponseEntity<FormVolunteerDto> getFormVolunteerById(@PathVariable("id") Long formId){
        return formVolunteerService.getFormVolunteerById(formId);
    }

    @PostMapping("/formVolunteer")
    public ResponseEntity<FormVolunteerCreatorDto> addFormVolunteer(@RequestBody FormVolunteerCreatorDto formVolunteerCreatorDto){
        return formVolunteerService.addFormVolunteer(formVolunteerCreatorDto);
    }

    @PutMapping("/formVolunteer/status/{id}")
    public ResponseEntity<FormStatus> updateStatusFormById(@PathVariable("id") Long formId, @RequestBody FormStatus formStatus){
        return formVolunteerService.updateFormVolunteerStatus(formId, formStatus);
    }

    @DeleteMapping("/formVolunteer/{id}")
    public ResponseEntity<Boolean> deleteFormVolunteerById(@PathVariable("id") Long formId){
        return formVolunteerService.deleteFormVolunteerById(formId);
    }
}
