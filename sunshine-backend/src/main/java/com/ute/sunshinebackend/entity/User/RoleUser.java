package com.ute.sunshinebackend.entity.User;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

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
