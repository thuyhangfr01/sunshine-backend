package com.ute.sunshinebackend.entity.Project;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;


@Data
@Entity(name = "projects")
public class Project {
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
//    @JsonIgnore
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

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "id_status")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "id_status")
    private long idStatus = 1;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = Timestamp.valueOf(LocalDateTime.now());

    public Project(String name, String details, Long numVolunteers, Date startTime, Date endTime, Date holdTime, String position) {
        this.name = name;
        this.details = details;
        this.numVolunteers = numVolunteers;
        this.startTime = startTime;
        this.endTime = endTime;
        this.holdTime = holdTime;
        this.position = position;
    }
}
