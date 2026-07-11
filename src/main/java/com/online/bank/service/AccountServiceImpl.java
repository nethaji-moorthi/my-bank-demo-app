package com.online.bank.service;

import com.online.bank.constants.ResponseConstants;
import com.online.bank.dto.AccountListResponse;
import com.online.bank.dto.SignupRequest;
import com.online.bank.dto.SignupResponse;
import com.online.bank.entity.Account;
import com.online.bank.repository.AccountRepository;
import com.online.bank.util.AccountNumberGenerator;
import com.online.bank.util.AccountValidationUtility;
import com.online.bank.util.TimestampUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private EmailService emailService;

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

        emailService.sendSignupConfirmation(request.getEmail(), request.getFirstName());

        return new SignupResponse(ResponseConstants.STATUS_SUCCESS, account, null, nowUtc, nowLocal);
    }

    @Override
    public AccountListResponse getAccounts(int limit, int offset) {
        logger.info("Fetching accounts with limit: {} and offset: {}", limit, offset);

        try {
            Pageable pageable = PageRequest.of(offset / limit, limit);
            Page<Account> page = accountRepository.findAll(pageable);

            logger.info("Fetched {} accounts out of {} total", page.getContent().size(), page.getTotalElements());

            return new AccountListResponse("SUCCESS", page.getContent(), limit, offset, page.getTotalElements(), null);
        } catch (Exception e) {
            logger.error("Error fetching accounts: {}", e.getMessage());
            return new AccountListResponse("ERROR", null, limit, offset, 0, e.getMessage());
        }
    }
}
