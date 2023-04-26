package com.ute.sunshinebackend.entity.Project;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity(name = "project_status")
public class ProjectStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy="projectStatus")
//    private List<Project> projectList = new ArrayList<>();
}
