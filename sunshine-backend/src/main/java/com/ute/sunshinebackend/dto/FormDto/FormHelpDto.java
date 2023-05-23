package com.ute.sunshinebackend.dto.FormDto;

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
    private long id;
    private String fullName;
    private String email;
    private String phone;
    private String title;
    private String contents;
    private String statusName;
    private Date createdAt;

    private List<FormImage> formImageList;

    public FormHelpDto(long id, String fullName, String email, String phone, String title, String contents, String statusName, Date createdAt, List<FormImage> formImageList) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.title = title;
        this.contents = contents;
        this.statusName = statusName;
        this.createdAt = createdAt;
        this.formImageList = formImageList;
    }

    public FormHelpDto() {
    }
}
