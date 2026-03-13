package com.example.mybankdemoapp.controller;

import com.example.mybankdemoapp.dto.SignupRequest;
import com.example.mybankdemoapp.dto.SignupResponse;
import com.example.mybankdemoapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "Account Management", description = "APIs for account signup and management")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/signup")
    @Operation(summary = "Create a new account", description = "Creates a new account with auto-generated account number and IFSC code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SignupResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or duplicate email/mobile"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest request) {
        SignupResponse response = accountService.signup(request);
        return ResponseEntity.ok(response);
    }
}