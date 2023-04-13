package com.ute.sunshine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Quyen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_Quyen;

    @Column(name = "TenQuyen", nullable = false)
    private String ten_Quyen;
}
