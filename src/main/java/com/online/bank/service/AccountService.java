package com.online.bank.service;

import com.online.bank.dto.AccountListResponse;
import com.online.bank.dto.SignupRequest;
import com.online.bank.dto.SignupResponse;

public interface AccountService {

    SignupResponse signup(SignupRequest request);

    AccountListResponse getAccounts(int limit, int offset);

}