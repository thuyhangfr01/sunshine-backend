package com.ute.sunshinebackend.entity.Contribution;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contribution_artifacts")
public class ContributionArtifact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artifact_name")
    private String artifactName;

    @Column(name = "donated_amount")
    private Long donatedAmount;

    @Column(name = "received_amount")
    private Long receivedAmount;

    @Column(name = "calculation_unit")
    private String calculationUnit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_status", nullable = false, columnDefinition = "int default 1")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private ContributionStatus contributionStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_contribution", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Contribution contribution;

    public ContributionArtifact(String artifactName, Long donatedAmount, String calculationUnit) {
        this.artifactName = artifactName;
        this.donatedAmount = donatedAmount;
        this.calculationUnit = calculationUnit;
    }
}
