package com.online.bank.service;

import com.online.bank.constants.ResponseConstants;
import com.online.bank.dto.SignupRequest;
import com.online.bank.dto.SignupResponse;
import com.online.bank.entity.Account;
import com.online.bank.repository.AccountRepository;
import com.online.bank.util.AccountNumberGenerator;
import com.online.bank.util.AccountValidationUtility;
import com.online.bank.util.TimestampUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountNumberGenerator accountNumberGenerator;

    @Autowired
    private AccountValidationUtility validationUtility;

    @Autowired
    private TimestampUtility timestampUtility;

    @Override
    public SignupResponse signup(SignupRequest request) {
        logger.info("Processing signup request for email: {}", request.getEmail());

        // Validate if email and mobile are unique
        SignupResponse validationError = validationUtility.validateUniqueEmailAndMobile(request);
        if (validationError != null) {
            return validationError;
        }

        // Generate credentials
        String accountNumber = accountNumberGenerator.generateAccountNumber();
        String ifsc = accountNumberGenerator.generateIFSC();

        // Create timestamps
        LocalDateTime nowUtc = timestampUtility.getUtcNow();
        LocalDateTime nowLocal = timestampUtility.getLocalNow();

        // Create and save account
        Account account = new Account(request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getMobile(), accountNumber, ifsc, nowUtc, nowLocal);
        account = accountRepository.save(account);

        // Log success
        logger.info("Account created successfully. ID: {}, Account Number: {}, Email: {}", 
                   account.getId(), accountNumber, request.getEmail());
        logger.debug("Account details - IFSC: {}, Name: {} {}", ifsc, request.getFirstName(), request.getLastName());

        return new SignupResponse(ResponseConstants.STATUS_SUCCESS, account, null, nowUtc, nowLocal);
    }
}
