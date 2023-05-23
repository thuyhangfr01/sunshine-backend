package com.ute.sunshinebackend.controller.Form;

import com.ute.sunshinebackend.dto.FormDto.FormVolunteerCreatorDto;
import com.ute.sunshinebackend.dto.FormDto.FormVolunteerDto;
import com.ute.sunshinebackend.entity.Form.FormStatus;
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
    public ResponseEntity<FormVolunteerCreatorDto> updateStatusFormById(@PathVariable("id") Long formId, @RequestBody FormVolunteerCreatorDto formVolunteerCreatorDto){
        return formVolunteerService.updateFormVolunteerStatus(formId, formVolunteerCreatorDto);
    }

    @DeleteMapping("/formVolunteer/{id}")
    public ResponseEntity<Boolean> deleteFormVolunteerById(@PathVariable("id") Long formId){
        return formVolunteerService.deleteFormVolunteerById(formId);
    }
}
