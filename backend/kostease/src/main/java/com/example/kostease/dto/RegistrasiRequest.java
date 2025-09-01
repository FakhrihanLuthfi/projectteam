package com.example.kostease.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrasiRequest {
    @NotBlank(message = "Username wajib diisi")
    private String username;

    @NotBlank(message = "Email wajib diisi")
    @Email(message = "Format email tidak valid")
    private String email;

    @NotBlank(message = "Password wajib diisi")
    private String password;

    @NotBlank(message = "Full name wajib diisi")
    private String fullName;

    private Long roleId;
}
