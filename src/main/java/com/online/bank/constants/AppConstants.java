package com.online.bank.constants;

/**
 * Application-wide constants for account-related operations
 */
public class AppConstants {

    // Account Number Generation Constants
    public static final String ACCOUNT_PREFIX = "2010400";
    public static final int ACCOUNT_SUFFIX_LENGTH = 5;
    public static final int ACCOUNT_NUMBER_RANDOM_LIMIT = 100000;

    // IFSC Code Generation Constants
    public static final String IFSC_PREFIX = "IFSC-";
    public static final int IFSC_SUFFIX_LENGTH = 5;
    public static final int IFSC_RANDOM_LIMIT = 100000;

    // Private constructor to prevent instantiation
    private AppConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
