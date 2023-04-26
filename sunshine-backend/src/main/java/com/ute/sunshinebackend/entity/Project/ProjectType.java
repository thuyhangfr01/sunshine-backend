package com.ute.sunshinebackend.entity.Project;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity(name = "project_types")
public class ProjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy="projectType")
//    private List<Project> projectList = new ArrayList<>();

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
