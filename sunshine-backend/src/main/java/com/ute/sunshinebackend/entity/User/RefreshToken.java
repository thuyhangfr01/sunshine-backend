package com.ute.sunshinebackend.entity.User;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity(name = "refreshtoken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, unique = true, name = "token")
    private String token;

    @Column(nullable = false, name = "expiry_date")
    private Instant expiryDate;
}
