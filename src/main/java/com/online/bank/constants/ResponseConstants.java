package com.online.bank.constants;

/**
 * Constants for API response statuses and error messages
 */
public class ResponseConstants {

    // Response Status Constants
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_ERROR = "ERROR";

    // Error Messages
    public static final String ERROR_EMAIL_EXISTS = "Email already exists";
    public static final String ERROR_MOBILE_EXISTS = "Mobile already exists";

    // Private constructor to prevent instantiation
    private ResponseConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
