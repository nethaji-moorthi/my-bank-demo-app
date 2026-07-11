package com.online.bank.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String ifsc;

    @Column(nullable = false)
    private LocalDateTime timestampUst;

    @Column(nullable = false)
    private LocalDateTime timestampLocal;

    // Constructors
    public Account() {}

    public Account(String firstName, String lastName, String email, String mobile, String accountNumber, String ifsc, LocalDateTime timestampUst, LocalDateTime timestampLocal) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.accountNumber = accountNumber;
        this.ifsc = ifsc;
        this.timestampUst = timestampUst;
        this.timestampLocal = timestampLocal;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
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