package com.ute.sunshinebackend.entity.Contribution;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contribution_money")
public class ContributionMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount_money")
    private Long amountMoney;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_status", nullable = false, columnDefinition = "int default 1")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private ContributionStatus contributionStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_contribution")
    private Contribution contribution;

    public ContributionMoney(Long amountMoney) {
        this.amountMoney = amountMoney;
    }

    public ContributionMoney() {
    }
}
