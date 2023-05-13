package com.ute.sunshinebackend.entity.Form;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "form_status")
public class FormStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
