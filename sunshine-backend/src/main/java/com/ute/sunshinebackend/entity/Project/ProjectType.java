package com.ute.sunshinebackend.entity.Project;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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

    @OneToMany(targetEntity = Project.class, mappedBy = "projectType", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Project> projects;

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
