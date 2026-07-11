package com.online.bank.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TimestampUtility {

    /**
     * Gets current UTC timestamp
     * @return UTC LocalDateTime
     */
    public LocalDateTime getUtcNow() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    /**
     * Gets current local timestamp
     * @return Local LocalDateTime
     */
    public LocalDateTime getLocalNow() {
        return LocalDateTime.now();
    }
}
