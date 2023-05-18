package com.ute.sunshinebackend.service.Form;

import com.ute.sunshinebackend.dto.FormDto.FormVolunteerCreatorDto;
import com.ute.sunshinebackend.dto.FormDto.FormVolunteerDto;
import com.ute.sunshinebackend.entity.Form.FormStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface FormVolunteerService {
    //get all latest form
    public ResponseEntity<List<FormVolunteerDto>> getLatestFormVolunteer();

    //get form by id
    public ResponseEntity<FormVolunteerDto> getFormVolunteerById(Long id);

    //add new form volunteer
    public ResponseEntity<FormVolunteerCreatorDto> addFormVolunteer(FormVolunteerCreatorDto formVolunteerCreatorDto);

    //update status form volunteer by id
    public ResponseEntity<FormStatus> updateFormVolunteerStatus(Long formId, FormStatus formStatus);

    //delete form volunteer by id
    public ResponseEntity<Boolean> deleteFormVolunteerById(Long formId);
}
