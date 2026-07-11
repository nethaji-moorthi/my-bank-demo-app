package com.example.mybankdemoapp.dto;

import com.example.mybankdemoapp.entity.Account;
import java.util.List;

public class AccountListResponse {

    private String status;
    private List<Account> data;
    private int limit;
    private int offset;
    private long total;
    private String error;

    // Constructors
    public AccountListResponse() {}

    public AccountListResponse(String status, List<Account> data, int limit, int offset, long total, String error) {
        this.status = status;
        this.data = data;
        this.limit = limit;
        this.offset = offset;
        this.total = total;
        this.error = error;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Account> getData() {
        return data;
    }

    public void setData(List<Account> data) {
        this.data = data;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
