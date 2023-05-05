package com.ute.sunshinebackend.entity.Project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Data
@Entity
@Table(name = "projects")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_type", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private ProjectType projectType;

    @Column(name = "num_volunteers")
    private Long numVolunteers;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name = "hold_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date holdTime;

    @Column(name = "position")
    private String position;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_status", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private ProjectStatus projectStatus;
//    @Column(name = "id_status")
//    private long idStatus = 1;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @OneToMany(targetEntity = ProjectImage.class, mappedBy = "project", orphanRemoval = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<ProjectImage> projectImages;

    @OneToMany(targetEntity = ProjectProof.class, mappedBy = "project", orphanRemoval = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<ProjectProof> projectProofs;

    @OneToMany(targetEntity = ProjectMoney.class, mappedBy = "project", orphanRemoval = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<ProjectMoney> projectMonies;

    @OneToMany(targetEntity = ProjectArtifact.class, mappedBy = "project", orphanRemoval = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<ProjectArtifact> projectArtifacts;

    public Project(String name, String details, Long numVolunteers, Date startTime, Date endTime, Date holdTime, String position) {
        this.name = name;
        this.details = details;
        this.numVolunteers = numVolunteers;
        this.startTime = startTime;
        this.endTime = endTime;
        this.holdTime = holdTime;
        this.position = position;
    }

    public Project(String name, String details, ProjectType projectType, Long numVolunteers, Date startTime, Date endTime, Date holdTime, String position, ProjectStatus projectStatus, List<ProjectImage> projectImages, List<ProjectProof> projectProofs, List<ProjectMoney> projectMonies, List<ProjectArtifact> projectArtifacts) {
        this.name = name;
        this.details = details;
        this.projectType = projectType;
        this.numVolunteers = numVolunteers;
        this.startTime = startTime;
        this.endTime = endTime;
        this.holdTime = holdTime;
        this.position = position;
        this.projectStatus = projectStatus;
        this.projectImages = projectImages;
        this.projectProofs = projectProofs;
        this.projectMonies = projectMonies;
        this.projectArtifacts = projectArtifacts;
    }

    public Project() {
    }
}
