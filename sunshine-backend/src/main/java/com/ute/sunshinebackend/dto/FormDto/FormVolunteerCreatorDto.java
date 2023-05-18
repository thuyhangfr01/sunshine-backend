package com.ute.sunshinebackend.dto.FormDto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class FormVolunteerCreatorDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String phone;
    private long projectId;
    private long statusId = 1;

    public FormVolunteerCreatorDto(long id, String email, String phone, long projectId, long statusId) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.projectId = projectId;
        this.statusId = statusId;
    }

    public FormVolunteerCreatorDto() {
    }
}
