package com.ute.sunshinebackend.entity.Project;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static org.hibernate.annotations.FetchMode.SELECT;

@Data
@Entity
@Table(name = "project_types")
public class ProjectType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy="projectType")
//    private List<Project> projectList = new ArrayList<>();

//    @OneToMany(mappedBy = "projectType")
//    private List<Project> projects;

    public ProjectType(String name) {
        this.name = name;
    }

    public ProjectType() {
    }

    public ProjectType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
