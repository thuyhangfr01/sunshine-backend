package com.ute.sunshinebackend.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
public class RoleUserId implements Serializable {
    private long idUser;

    private long idRole;

    // default constructor

    public RoleUserId(long userId, long roleId) {
        this.idUser = userId;
        this.idRole = roleId;
    }
}
