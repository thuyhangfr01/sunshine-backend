package com.ute.sunshinebackend.security.payload.responses;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String street;
    private Object roles;

    public JwtResponse(String accessToken, String refreshToken, Long id, String name, String email, String phone, String password, String street, Object roles) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.street = street;
        this.roles = roles;
    }

    public JwtResponse(String accessToken, String refreshToken, Long id, String name, Object roles) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.name = name;
        this.roles = roles;
    }
}
