package com.ute.sunshinebackend.security.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 10)
    private String phone;

    private String street;

    private long id_ward;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> role;

    public SignupRequest(String name, String email, String phone, String password, Set<String> role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public SignupRequest(String name, String email, String phone, String street, long id_ward, String password, Set<String> role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.id_ward = id_ward;
        this.password = password;
        this.role = role;
    }
}
