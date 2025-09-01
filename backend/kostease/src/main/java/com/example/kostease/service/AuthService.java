package com.example.kostease.service;

import com.example.kostease.dto.*;
import com.example.kostease.entity.User;
import com.example.kostease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // REGISTER
    public Map<String, Object> register(RegistrasiRequest req) {
        Map<String, Object> response = new HashMap<>();

        if (req == null) {
            response.put("success", false);
            response.put("message", "Data registrasi tidak boleh kosong");
            return response;
        }

        if (!StringUtils.hasText(req.getEmail()) || !StringUtils.hasText(req.getPassword())) {
            response.put("success", false);
            response.put("message", "Email dan password wajib diisi");
            return response;
        }

        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            response.put("success", false);
            response.put("message", "Email sudah digunakan");
            return response;
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setFullName(req.getFullName());

        // Validasi roleId — jika tidak ada, set default (misal: 2 = user biasa)
        user.setRoleId(req.getRoleId() != null ? req.getRoleId() : 2);
        user.setStatus("aktif");
        user.setLastLogin(null);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        try {
            userRepository.save(user);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Gagal menyimpan pengguna: " + e.getMessage());
            return response;
        }

        // JANGAN kembalikan objek User (bisa bocor password hash)
        Map<String, Object> userData = new HashMap<>();
        userData.put("id", user.getId());
        userData.put("username", user.getUsername());
        userData.put("email", user.getEmail());
        userData.put("fullName", user.getFullName());
        userData.put("roleId", user.getRoleId());
        userData.put("status", user.getStatus());

        response.put("success", true);
        response.put("message", "Registrasi berhasil");
        response.put("data", userData);

        return response;
    }

    // LOGIN
    public Map<String, Object> login(LoginRequest req) {
        Map<String, Object> response = new HashMap<>();

        if (req == null || !StringUtils.hasText(req.getEmail()) || !StringUtils.hasText(req.getPassword())) {
            response.put("success", false);
            response.put("message", "Email dan password wajib diisi");
            return response;
        }

        Optional<User> userOpt = userRepository.findByEmail(req.getEmail());
        if (userOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "Email tidak ditemukan");
            return response;
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            response.put("success", false);
            response.put("message", "Password salah");
            return response;
        }

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        Map<String, Object> userData = new HashMap<>();
        userData.put("id", user.getId());
        userData.put("username", user.getUsername());
        userData.put("email", user.getEmail());
        userData.put("fullName", user.getFullName());
        userData.put("roleId", user.getRoleId());
        userData.put("status", user.getStatus());
        userData.put("lastLogin", user.getLastLogin());

        response.put("success", true);
        response.put("message", "Login berhasil");
        response.put("data", userData);

        return response;
    }

    // REQUEST RESET PASSWORD
    public Map<String, Object> requestResetPassword(ResetPasswordRequest req) {
        Map<String, Object> response = new HashMap<>();

        if (req == null || !StringUtils.hasText(req.getEmail())) {
            response.put("success", false);
            response.put("message", "Email wajib diisi");
            return response;
        }

        Optional<User> userOpt = userRepository.findByEmail(req.getEmail());
        if (userOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "Email tidak ditemukan");
            return response;
        }

        User user = userOpt.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(30));
        userRepository.save(user);

        response.put("success", true);
        response.put("message", "Token reset password berhasil dibuat");
        response.put("token", token); // Bisa dikirim via email, jangan selalu tampilkan

        return response;
    }

    // VALIDASI TOKEN RESET PASSWORD
    public Map<String, Object> validateResetToken(ResetPasswordValidateRequest req) {
        Map<String, Object> response = new HashMap<>();

        if (req == null || !StringUtils.hasText(req.getToken())) {
            response.put("success", false);
            response.put("message", "Token tidak boleh kosong");
            return response;
        }

        Optional<User> userOpt = userRepository.findByResetToken(req.getToken());
        if (userOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "Token tidak valid");
            return response;
        }

        User user = userOpt.get();
        if (user.getResetTokenExpiry() == null || user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            response.put("success", false);
            response.put("message", "Token sudah kadaluarsa");
            return response;
        }

        response.put("success", true);
        response.put("message", "Token valid");
        return response;
    }

    // KONFIRMASI RESET PASSWORD
    public Map<String, Object> confirmResetPassword(ResetPasswordConfirmRequest req) {
        Map<String, Object> response = new HashMap<>();

        if (req == null || !StringUtils.hasText(req.getToken()) || !StringUtils.hasText(req.getNewPassword())) {
            response.put("success", false);
            response.put("message", "Token dan password baru wajib diisi");
            return response;
        }

        if (req.getNewPassword().length() < 6) {
            response.put("success", false);
            response.put("message", "Password minimal 6 karakter");
            return response;
        }

        Optional<User> userOpt = userRepository.findByResetToken(req.getToken());
        if (userOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "Token tidak valid");
            return response;
        }

        User user = userOpt.get();
        if (user.getResetTokenExpiry() == null || user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            response.put("success", false);
            response.put("message", "Token sudah kadaluarsa");
            return response;
        }

        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        user.setUpdatedAt(LocalDateTime.now());

        try {
            userRepository.save(user);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Gagal mengubah password: " + e.getMessage());
            return response;
        }

        response.put("success", true);
        response.put("message", "Password berhasil direset");
        return response;
    }
}