package com.ute.sunshinebackend.exception;

public class RoleNotFound extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RoleNotFound(String msg) {
        super(msg);
    }
}
