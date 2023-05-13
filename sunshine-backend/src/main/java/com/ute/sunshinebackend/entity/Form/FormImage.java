package com.ute.sunshinebackend.entity.Form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ute.sunshinebackend.entity.Project.Project;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "form_images")
public class FormImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_form_help", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FormHelp formHelp;

    @Column(name = "name")
    private String name;

}
