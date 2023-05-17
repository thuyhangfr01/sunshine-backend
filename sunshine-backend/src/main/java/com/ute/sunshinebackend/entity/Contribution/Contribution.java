package com.ute.sunshinebackend.entity.Contribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.entity.Project.ProjectArtifact;
import com.ute.sunshinebackend.entity.User;
import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contributions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contribution {
    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator",
            strategy = "com.ute.sunshinebackend.generator.MyGenerator")
    @Column(name="id")
    private String id;

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

//    @OneToOne(mappedBy = "contribution", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private ContributionMoney contributionMoney;

    @OneToMany(targetEntity = ContributionArtifact.class, mappedBy = "contribution", orphanRemoval = false, fetch = FetchType.LAZY)
    @Fetch(value=FetchMode.SELECT)
    @Cascade(CascadeType.ALL)
    private List<ContributionArtifact> contributionArtifacts;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = Timestamp.valueOf(LocalDateTime.now());

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void addArtifact(List<ContributionArtifact> contributionArtifacts){
        this.contributionArtifacts = contributionArtifacts;
        contributionArtifacts.forEach(contributionArtifact -> contributionArtifact.setContribution(this));
    }
}
