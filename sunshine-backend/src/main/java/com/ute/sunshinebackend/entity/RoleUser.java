package com.ute.sunshinebackend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "roles_users")
@IdClass(RoleUserId.class)
public class RoleUser {
    @Id
    @Column(name = "id_user")
    private long idUser;

    @Id
    @Column(name = "id_role")
    private int idRole;
}
