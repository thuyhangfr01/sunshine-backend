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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contribution_money", referencedColumnName = "id")
    private ContributionMoney contributionMoney;

    @OneToMany(targetEntity = ContributionArtifact.class, mappedBy = "contribution", orphanRemoval = false, fetch = FetchType.LAZY)
    @Fetch(value=FetchMode.SELECT)
    private List<ContributionArtifact> contributionArtifacts;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = Timestamp.valueOf(LocalDateTime.now());

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ContributionMoney getContributionMoney() {
        return contributionMoney;
    }

    public void setContributionMoney(ContributionMoney contributionMoney) {
        this.contributionMoney = contributionMoney;
    }

    public List<ContributionArtifact> getContributionArtifacts() {
        return contributionArtifacts;
    }

    public void setContributionArtifacts(List<ContributionArtifact> contributionArtifacts) {
        this.contributionArtifacts = contributionArtifacts;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    //    public void setContributionMoney(ContributionMoney contributionMoney) {
//        this.contributionMoney = contributionMoney;
//        this.contributionMoney.setContribution(this);
//    }

//    public void addArtifact(List<ContributionArtifact> contributionArtifacts){
//        this.contributionArtifacts = contributionArtifacts;
//        contributionArtifacts.forEach(contributionArtifact -> contributionArtifact.setContribution(this));
//    }
}
