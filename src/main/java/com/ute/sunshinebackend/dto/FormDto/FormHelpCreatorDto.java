package com.ute.sunshinebackend.dto.FormDto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class FormHelpCreatorDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String email;
    private String phone;
    private String title;
    private String contents;
    private long statusId = 1;

    public FormHelpCreatorDto(long id, String fullName, String email, String phone, String title, String contents, long statusId) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.title = title;
        this.contents = contents;
        this.statusId = statusId;
    }

    public FormHelpCreatorDto() {
    }
}
