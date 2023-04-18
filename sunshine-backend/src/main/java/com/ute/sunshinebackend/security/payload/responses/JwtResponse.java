package com.ute.sunshinebackend.security.payload.responses;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String street;
    private long id_ward;
    private Object roles;

    public JwtResponse(String token, long id, String name, String email, String phone, String password, String street, long id_ward, Object roles) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.street = street;
        this.id_ward = id_ward;
        this.roles = roles;
    }
}
