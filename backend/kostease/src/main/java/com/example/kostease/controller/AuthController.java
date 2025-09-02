package com.example.kostease.controller;

import com.example.kostease.dto.*;
import com.example.kostease.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final KamarService kamarService;
    private final PenyewaService penyewaService;
    private final PembayaranService pembayaranService;

    public AuthController(
            AuthService authService,
            KamarService kamarService,
            PenyewaService penyewaService,
            PembayaranService pembayaranService
    ) {
        this.authService = authService;
        this.kamarService = kamarService;
        this.penyewaService = penyewaService;
        this.pembayaranService = pembayaranService;
    }

    // ==================== AUTH ====================
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegistrasiRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/auth/request-reset-password")
    public ResponseEntity<?> requestResetPassword(@RequestBody ResetPasswordRequest req) {
        return ResponseEntity.ok(authService.requestResetPassword(req));
    }

    @PostMapping("/auth/validate-reset-token")
    public ResponseEntity<?> validateResetToken(@RequestBody ResetPasswordValidateRequest req) {
        return ResponseEntity.ok(authService.validateResetToken(req));
    }

    @PostMapping("/auth/confirm-reset-password")
    public ResponseEntity<?> confirmResetPassword(@RequestBody ResetPasswordConfirmRequest req) {
        return ResponseEntity.ok(authService.confirmResetPassword(req));
    }

    // ==================== KAMAR ====================
    @PostMapping("/kamar")
    public ResponseEntity<?> createKamar(@RequestBody KamarRequest req) {
        return ResponseEntity.ok(kamarService.create(req));
    }

    @GetMapping("/kamar")
    public ResponseEntity<?> getAllKamar() {
        return ResponseEntity.ok(kamarService.getAll());
    }

    @GetMapping("/kamar/{id}")
    public ResponseEntity<?> getKamarById(@PathVariable Long id) {
        return ResponseEntity.ok(kamarService.getById(id));
    }

    @PutMapping("/kamar/{id}")
    public ResponseEntity<?> updateKamar(@PathVariable Long id, @RequestBody KamarRequest req) {
        return ResponseEntity.ok(kamarService.update(id, req));
    }

    @DeleteMapping("/kamar/{id}")
    public ResponseEntity<?> deleteKamar(@PathVariable Long id) {
        return ResponseEntity.ok(kamarService.delete(id));
    }

    // ==================== PENYEWA ====================
    @PostMapping("/penyewa")
    public ResponseEntity<?> createPenyewa(@RequestBody PenyewaRequest req) {
        return ResponseEntity.ok(penyewaService.create(req));
    }

    @GetMapping("/penyewa")
    public ResponseEntity<?> getAllPenyewa() {
        return ResponseEntity.ok(penyewaService.getAll());
    }

    @GetMapping("/penyewa/{id}")
    public ResponseEntity<?> getPenyewaById(@PathVariable Long id) {
        return ResponseEntity.ok(penyewaService.getById(id));
    }

    @PutMapping("/penyewa/{id}")
    public ResponseEntity<?> updatePenyewa(@PathVariable Long id, @RequestBody PenyewaRequest req) {
        return ResponseEntity.ok(penyewaService.update(id, req));
    }

    @DeleteMapping("/penyewa/{id}")
    public ResponseEntity<?> deletePenyewa(@PathVariable Long id) {
        return ResponseEntity.ok(penyewaService.delete(id));
    }

    // ==================== PEMBAYARAN ====================
    @PostMapping("/pembayaran")
    public ResponseEntity<?> createPembayaran(@RequestBody PembayaranRequest req) {
        return ResponseEntity.ok(pembayaranService.create(req));
    }

    @GetMapping("/pembayaran")
    public ResponseEntity<?> getAllPembayaran() {
        return ResponseEntity.ok(pembayaranService.getAll());
    }

    @GetMapping("/pembayaran/{id}")
    public ResponseEntity<?> getPembayaranById(@PathVariable Long id) {
        return ResponseEntity.ok(pembayaranService.getById(id));
    }

    @PutMapping("/pembayaran/{id}")
    public ResponseEntity<?> updatePembayaran(@PathVariable Long id, @RequestBody PembayaranRequest req) {
        return ResponseEntity.ok(pembayaranService.update(id, req));
    }

    @DeleteMapping("/pembayaran/{id}")
    public ResponseEntity<?> deletePembayaran(@PathVariable Long id) {
        return ResponseEntity.ok(pembayaranService.delete(id));
    }
}
