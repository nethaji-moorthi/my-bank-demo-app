package com.example.mybankdemoapp.service;

import com.example.mybankdemoapp.dto.SignupRequest;
import com.example.mybankdemoapp.dto.SignupResponse;

public interface AccountService {

    SignupResponse signup(SignupRequest request);

}