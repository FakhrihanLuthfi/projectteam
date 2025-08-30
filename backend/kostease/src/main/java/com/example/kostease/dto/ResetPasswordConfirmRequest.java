package com.example.kostease.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordConfirmRequest {
    @NotBlank(message = "Token wajib diisi")
    private String token;

    @NotBlank(message = "Password baru wajib diisi")
    private String newPassword;
}
