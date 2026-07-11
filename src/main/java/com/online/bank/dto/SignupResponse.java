package com.online.bank.dto;

import com.online.bank.entity.Account;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class SignupResponse {

    private String status;
    private Account data;
    private String error;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestampUst;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestampLocal;

    // Constructors
    public SignupResponse() {}

    public SignupResponse(String status, Account data, String error, LocalDateTime timestampUst, LocalDateTime timestampLocal) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.timestampUst = timestampUst;
        this.timestampLocal = timestampLocal;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getData() {
        return data;
    }

    public void setData(Account data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestampUst() {
        return timestampUst;
    }

    public void setTimestampUst(LocalDateTime timestampUst) {
        this.timestampUst = timestampUst;
    }

    public LocalDateTime getTimestampLocal() {
        return timestampLocal;
    }

    public void setTimestampLocal(LocalDateTime timestampLocal) {
        this.timestampLocal = timestampLocal;
    }
}