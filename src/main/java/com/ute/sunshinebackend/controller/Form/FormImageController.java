package com.ute.sunshinebackend.controller.Form;

import com.ute.sunshinebackend.entity.Form.FormImage;
import com.ute.sunshinebackend.service.Form.FormImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FormImageController {
    @Autowired
    FormImageService formImageService;

    @PostMapping("/formHelp/{id}/image")
    public ResponseEntity<FormImage> addImage(@PathVariable("id") Long formHelpId, @RequestBody FormImage formImage){
        return formImageService.addImage(formHelpId, formImage);
    }
}
