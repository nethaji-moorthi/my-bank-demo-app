package com.example.mybankdemoapp.service;

import com.example.mybankdemoapp.dto.SignupRequest;
import com.example.mybankdemoapp.dto.SignupResponse;
import com.example.mybankdemoapp.entity.Account;
import com.example.mybankdemoapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    private final Random random = new Random();

    public SignupResponse signup(SignupRequest request) {
        logger.info("Processing signup request for email: {}", request.getEmail());
        
        // Check if email or mobile already exists
        if (accountRepository.existsByEmail(request.getEmail())) {
            logger.warn("Signup failed: Email already exists - {}", request.getEmail());
            return new SignupResponse("ERROR", null, "Email already exists", LocalDateTime.now(ZoneOffset.UTC), LocalDateTime.now());
        }
        if (accountRepository.existsByMobile(request.getMobile())) {
            logger.warn("Signup failed: Mobile already exists - {}", request.getMobile());
            return new SignupResponse("ERROR", null, "Mobile already exists", LocalDateTime.now(ZoneOffset.UTC), LocalDateTime.now());
        }

        // Generate account number: 2010400 + 5 random digits
        String accountNumber = "2010400" + String.format("%05d", random.nextInt(100000));

        // Generate IFSC: IFSC- + 5 random digits
        String ifsc = "IFSC-" + String.format("%05d", random.nextInt(100000));

        // Timestamps
        LocalDateTime nowUtc = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime nowLocal = LocalDateTime.now();

        // Create account
        Account account = new Account(request.getFirstName(), request.getLastName(), request.getEmail(), request.getMobile(),
                accountNumber, ifsc, nowUtc, nowLocal);

        account = accountRepository.save(account);

        logger.info("Account created successfully. ID: {}, Account Number: {}, Email: {}", account.getId(), accountNumber, request.getEmail());
        logger.debug("Account details - IFSC: {}, Name: {} {}", ifsc, request.getFirstName(), request.getLastName());

        return new SignupResponse("SUCCESS", account, null, nowUtc, nowLocal);
    }
}