package com.ute.sunshinebackend.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransactionDto implements Serializable {
    private String status;
    private String message;
    private String data;
}
