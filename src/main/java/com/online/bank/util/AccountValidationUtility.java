package com.online.bank.util;

import com.online.bank.constants.ResponseConstants;
import com.online.bank.dto.SignupRequest;
import com.online.bank.dto.SignupResponse;
import com.online.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class AccountValidationUtility {

    private static final Logger logger = LoggerFactory.getLogger(AccountValidationUtility.class);

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Validates if email and mobile are unique in the system
     * @param request Signup request containing email and mobile
     * @return SignupResponse with error if validation fails, null if all validations pass
     */
    public SignupResponse validateUniqueEmailAndMobile(SignupRequest request) {
        LocalDateTime nowUtc = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime nowLocal = LocalDateTime.now();

        if (accountRepository.existsByEmail(request.getEmail())) {
            logger.warn("Signup failed: Email already exists - {}", request.getEmail());
            return new SignupResponse(ResponseConstants.STATUS_ERROR, null, ResponseConstants.ERROR_EMAIL_EXISTS, nowUtc, nowLocal);
        }

        if (accountRepository.existsByMobile(request.getMobile())) {
            logger.warn("Signup failed: Mobile already exists - {}", request.getMobile());
            return new SignupResponse(ResponseConstants.STATUS_ERROR, null, ResponseConstants.ERROR_MOBILE_EXISTS, nowUtc, nowLocal);
        }

        return null;
    }
}
