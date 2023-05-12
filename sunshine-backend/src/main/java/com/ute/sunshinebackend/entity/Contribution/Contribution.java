package com.ute.sunshinebackend.entity.Contribution;

import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.entity.Project.ProjectArtifact;
import com.ute.sunshinebackend.entity.User;
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
@Table(name = "contributions")
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "messages")
    private String messages;

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

    @OneToOne(mappedBy = "contribution", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ContributionMoney contributionMoney;

    @OneToMany(targetEntity = ContributionArtifact.class, mappedBy = "contribution", orphanRemoval = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<ContributionArtifact> contributionArtifacts;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = Timestamp.valueOf(LocalDateTime.now());

    public void setContributionMoney(ContributionMoney contributionMoney) {
        this.contributionMoney = contributionMoney;
        this.contributionMoney.setContribution(this);
    }
}
