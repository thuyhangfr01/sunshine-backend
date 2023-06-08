package com.ute.sunshinebackend.service.Form;

import com.ute.sunshinebackend.entity.Form.FormImage;
import org.springframework.http.ResponseEntity;

public interface FormImageService {
    //add image of form id
    public ResponseEntity<FormImage> addImage(Long formId, FormImage formImage);
}
