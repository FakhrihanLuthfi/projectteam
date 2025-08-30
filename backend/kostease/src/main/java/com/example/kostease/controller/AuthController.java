// File: AuthController.java
package com.example.kostease.controller;

import com.example.kostease.dto.*;
import com.example.kostease.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrasiRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/request-reset-password")
    public ResponseEntity<?> requestResetPassword(@RequestBody ResetPasswordRequest req) {
        return ResponseEntity.ok(authService.requestResetPassword(req));
    }

    @PostMapping("/validate-reset-token")
    public ResponseEntity<?> validateResetToken(@RequestBody ResetPasswordValidateRequest req) {
        return ResponseEntity.ok(authService.validateResetToken(req));
    }

    @PostMapping("/confirm-reset-password")
    public ResponseEntity<?> confirmResetPassword(@RequestBody ResetPasswordConfirmRequest req) {
        return ResponseEntity.ok(authService.confirmResetPassword(req));
    }
}