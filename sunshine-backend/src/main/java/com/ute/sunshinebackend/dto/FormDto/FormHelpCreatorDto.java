package com.ute.sunshinebackend.dto.FormDto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class FormHelpCreatorDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String title;
    private String contents;
    private long statusId = 1;

    public FormHelpCreatorDto(long id, long userId, String title, String contents, long statusId) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.statusId = statusId;
    }

    public FormHelpCreatorDto() {
    }
}
