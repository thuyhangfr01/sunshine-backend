package com.ute.sunshinebackend.security.responses;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
public class UserInfo {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String street;
    private long id_ward;

    private Object roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getId_ward() {
        return id_ward;
    }

    public void setId_ward(long id_ward) {
        this.id_ward = id_ward;
    }

    public Object getRoles() {
        return roles;
    }

    public void setRoles(Object roles) {
        this.roles = roles;
    }
}
