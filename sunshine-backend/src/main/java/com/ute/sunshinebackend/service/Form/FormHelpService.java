package com.ute.sunshinebackend.service.Form;

import com.ute.sunshinebackend.dto.FormDto.FormHelpCreatorDto;
import com.ute.sunshinebackend.dto.FormDto.FormHelpDto;
import com.ute.sunshinebackend.entity.Form.FormStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface FormHelpService {
    //get all latest form
    public ResponseEntity<List<FormHelpDto>> getLatestFormHelp();

    //get form by id
    public ResponseEntity<FormHelpDto> getFormHelpById(Long formId);

    //get all latest form by user id
    public ResponseEntity<List<FormHelpDto>> getLatestFormHelpByUserId(Long userId);

    //create new form help
    public ResponseEntity<FormHelpCreatorDto> addFormHelp(FormHelpCreatorDto formHelpCreatorDto);

    //update form by id
    public ResponseEntity<FormHelpCreatorDto> updateFormHelpById(Long formId, FormHelpCreatorDto formHelpCreatorDto);

    //update status form by id
    public ResponseEntity<FormStatus> updateFormHelpStatus(Long formId, FormStatus formStatus);

    //delete form by id
    public ResponseEntity<Boolean> deleteFormHelpById(Long formId);
}
