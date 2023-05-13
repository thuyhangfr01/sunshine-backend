package com.ute.sunshinebackend.service.Form;

import com.ute.sunshinebackend.entity.Form.FormImage;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Form.FormHelpRepository;
import com.ute.sunshinebackend.repository.Form.FormImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FormImageServiceImpl implements FormImageService{
    @Autowired
    FormHelpRepository formHelpRepository;

    @Autowired
    FormImageRepository formImageRepository;

    @Override
    public ResponseEntity<FormImage> addImage(Long formId, FormImage formImage) {
        try {
            FormImage formImage1 = formHelpRepository.findById(formId).map(formHelp -> {
                formImage.setFormHelp(formHelp);
                return formImageRepository.save(formImage);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found form wwith id = " + formId));

            return new ResponseEntity<>(formImage1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
