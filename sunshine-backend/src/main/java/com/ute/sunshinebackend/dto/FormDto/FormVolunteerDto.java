package com.ute.sunshinebackend.dto.FormDto;
import lombok.Data;

import java.util.Date;

@Data
public class FormVolunteerDto {
    private String email;
    private String phone;
    private String projectName;
    private String statusName;
    private Date createdAt;

    public FormVolunteerDto() {
    }

    public FormVolunteerDto(String email, String phone, String projectName, String statusName, Date createdAt) {
        this.email = email;
        this.phone = phone;
        this.projectName = projectName;
        this.statusName = statusName;
        this.createdAt = createdAt;
    }
}
