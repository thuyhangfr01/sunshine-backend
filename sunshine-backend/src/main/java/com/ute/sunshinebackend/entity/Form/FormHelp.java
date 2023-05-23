package com.ute.sunshinebackend.entity.Form;

import com.ute.sunshinebackend.entity.User.User;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "form_help")
public class FormHelp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;
    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_status", referencedColumnName = "id", columnDefinition = "INT DEFAUT 1")
    private FormStatus formStatus;

    @OneToMany(targetEntity = FormImage.class, mappedBy = "formHelp", orphanRemoval = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<FormImage> formImages;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = Timestamp.valueOf(LocalDateTime.now());
}
