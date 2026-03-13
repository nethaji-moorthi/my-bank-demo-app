package com.example.mybankdemoapp;

import com.example.mybankdemoapp.dto.SignupRequest;
import com.example.mybankdemoapp.dto.SignupResponse;
import com.example.mybankdemoapp.entity.Account;
import com.example.mybankdemoapp.repository.AccountRepository;
import com.example.mybankdemoapp.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void testSignupSuccess() {
        SignupRequest request = new SignupRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setMobile("1234567890");

        when(accountRepository.existsByEmail("john.doe@example.com")).thenReturn(false);
        when(accountRepository.existsByMobile("1234567890")).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> {
            Account account = invocation.getArgument(0);
            account.setId(1L);
            return account;
        });

        SignupResponse response = accountService.signup(request);

        assertEquals("SUCCESS", response.getStatus());
        assertNotNull(response.getData());
        assertNull(response.getError());
        assertEquals("John", response.getData().getFirstName());
        assertEquals("Doe", response.getData().getLastName());
        assertEquals("john.doe@example.com", response.getData().getEmail());
        assertEquals("1234567890", response.getData().getMobile());
        assertTrue(response.getData().getAccountNumber().startsWith("2010400"));
        assertTrue(response.getData().getIfsc().startsWith("IFSC-"));
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testSignupEmailExists() {
        SignupRequest request = new SignupRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setMobile("1234567890");

        when(accountRepository.existsByEmail("john.doe@example.com")).thenReturn(true);

        SignupResponse response = accountService.signup(request);

        assertEquals("ERROR", response.getStatus());
        assertNull(response.getData());
        assertEquals("Email already exists", response.getError());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void testSignupMobileExists() {
        SignupRequest request = new SignupRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setMobile("1234567890");

        when(accountRepository.existsByEmail("john.doe@example.com")).thenReturn(false);
        when(accountRepository.existsByMobile("1234567890")).thenReturn(true);

        SignupResponse response = accountService.signup(request);

        assertEquals("ERROR", response.getStatus());
        assertNull(response.getData());
        assertEquals("Mobile already exists", response.getError());
        verify(accountRepository, never()).save(any(Account.class));
    }
}