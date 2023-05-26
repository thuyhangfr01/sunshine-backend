package com.ute.sunshinebackend.dto.FormDto;
import lombok.Data;

import java.util.Date;

@Data
public class FormVolunteerDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String projectName;
    private Long projectId;
    private String statusName;
    private Date createdAt;

    public FormVolunteerDto() {
    }

    public FormVolunteerDto(Long id, String fullName, String email, String phone, String projectName, Long projectId, String statusName, Date createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.projectName = projectName;
        this.projectId = projectId;
        this.statusName = statusName;
        this.createdAt = createdAt;
    }
}
