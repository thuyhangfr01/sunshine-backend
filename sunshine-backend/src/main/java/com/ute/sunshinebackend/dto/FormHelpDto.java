package com.ute.sunshinebackend.dto;

import com.ute.sunshinebackend.entity.Form.FormImage;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.*;

@Data
public class FormHelpDto {
    private String userName;
    private String userEmail;
    private String userPhone;
    private String title;
    private String contents;
    private String statusName;
    private Date createdAt;

    private List<FormImage> formImageList;

    public FormHelpDto(String userName, String userEmail, String userPhone, String title, String contents, String statusName, Date createdAt, List<FormImage> formImageList) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.title = title;
        this.contents = contents;
        this.statusName = statusName;
        this.createdAt = createdAt;
        this.formImageList = formImageList;
    }

    public FormHelpDto() {
    }
}
