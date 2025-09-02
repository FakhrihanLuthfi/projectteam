package com.example.kostease.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class KamarRequest {
    @NotBlank(message = "Nomor kamar wajib diisi")
    private String nomorKamar;

    @NotBlank(message = "Tipe kamar wajib diisi")
    private String tipeKamar;

    @NotNull(message = "Harga wajib diisi")
    private BigDecimal harga;

    private String status = "TERSEDIA"; // default

    private String gambar; // path atau URL gambar
}
