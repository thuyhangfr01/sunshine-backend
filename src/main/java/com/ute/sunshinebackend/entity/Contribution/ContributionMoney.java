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

    @Column(name = "amount_money", columnDefinition = "INT DEFAULT 0")
    private Long amountMoney;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_status", referencedColumnName = "id", columnDefinition = "INT DEFAUT 1")
    private ContributionStatus mcontributionStatus;

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "id_contribution")
//    private Contribution contribution;

    public ContributionMoney() {
    }
}
