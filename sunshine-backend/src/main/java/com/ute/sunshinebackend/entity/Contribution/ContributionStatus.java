package com.ute.sunshinebackend.entity.Contribution;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contribution_status")
public class ContributionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
