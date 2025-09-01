package com.example.kostease.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PembayaranRequest {
    @NotNull(message = "Penyewa ID wajib diisi")
    private Long penyewaId;

    @NotNull(message = "Kamar ID wajib diisi")
    private Long kamarId;

    @NotNull(message = "Jumlah pembayaran wajib diisi")
    private BigDecimal jumlah;

    private String metode;  // CASH / TRANSFER / QRIS
    private String status = "PENDING"; // default
    private String keterangan;
}

