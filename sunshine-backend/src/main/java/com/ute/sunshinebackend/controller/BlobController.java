package com.ute.sunshinebackend.controller;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BlobController {
    @Autowired
    private BlobContainerClient blobContainerClient;

    @PostMapping("/blob/upload")
//    @RequestMapping(value = "/fileupload/file", headers = ("content-type=multipart/*"), method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFiles(@RequestParam("file") MultipartFile file) {
        // Get file name
        String fileName = file.getOriginalFilename();

        // Create blob client
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);

        // Upload file to blob storage
        try {
            blobClient.upload(file.getInputStream(), file.getSize());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(blobClient.getBlobUrl(), HttpStatus.OK);
    }
}
