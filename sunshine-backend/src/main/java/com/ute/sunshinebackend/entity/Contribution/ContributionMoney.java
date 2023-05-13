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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private ContributionStatus mcontributionStatus;

//    @OneToOne(mappedBy = "contributionMoney")
//    private Contribution contribution;

    public ContributionMoney() {
    }
}
