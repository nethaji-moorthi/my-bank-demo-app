package com.online.bank.util;

import com.online.bank.constants.AppConstants;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AccountNumberGenerator {

    private final Random random = new Random();

    /**
     * Generates a unique account number with format: 2010400 + 5 random digits
     * @return Generated account number
     */
    public String generateAccountNumber() {
        return AppConstants.ACCOUNT_PREFIX + String.format("%0" + AppConstants.ACCOUNT_SUFFIX_LENGTH + "d", 
               random.nextInt(AppConstants.ACCOUNT_NUMBER_RANDOM_LIMIT));
    }

    /**
     * Generates a unique IFSC code with format: IFSC- + 5 random digits
     * @return Generated IFSC code
     */
    public String generateIFSC() {
        return AppConstants.IFSC_PREFIX + String.format("%0" + AppConstants.IFSC_SUFFIX_LENGTH + "d", 
               random.nextInt(AppConstants.IFSC_RANDOM_LIMIT));
    }
}
