package com.ute.sunshinebackend.entity.Project;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "project_status")
public class ProjectStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Project.class, mappedBy = "projectStatus", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Project> projects;
}
