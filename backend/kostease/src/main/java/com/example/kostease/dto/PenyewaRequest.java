package com.example.kostease.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PenyewaRequest {
    @NotBlank(message = "Nama lengkap wajib diisi")
    private String namaLengkap;

    @NotBlank(message = "Nomor HP wajib diisi")
    private String noHp;

    @Email(message = "Format email tidak valid")
    @NotBlank(message = "Email wajib diisi")
    private String email;

    private String alamat;

    @NotNull(message = "User ID wajib diisi")
    private Long userId;

    @NotNull(message = "Kamar ID wajib diisi")
    private Long kamarId;

    @NotNull(message = "Tanggal masuk wajib diisi")
    private LocalDate tanggalMasuk;

    private LocalDate tanggalKeluar;
}
