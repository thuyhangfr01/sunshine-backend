package com.ute.sunshinebackend.entity.Project;

import com.ute.sunshinebackend.entity.User.User;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "project_payment")
public class ProjectPayment {
    @Id
    @GeneratedValue(generator = "my_generatorP")
    @GenericGenerator(name = "my_generatorP",
            strategy = "com.ute.sunshinebackend.generator.MyGeneratorP")
    @Column(name="id")
    private String id;

    @Column(name = "amount_money")
    private long amountMoney;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = Timestamp.valueOf(LocalDateTime.now());

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_project", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private Project project;
}
