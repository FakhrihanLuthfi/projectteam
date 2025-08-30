package com.example.kostease.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordValidateRequest {
    @NotBlank(message = "Token wajib diisi")
    private String token;
}
