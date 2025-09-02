package com.example.kostease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pembayaran")
public class Pembayaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relasi ke penyewa
    @ManyToOne
    @JoinColumn(name = "penyewa_id", nullable = false)
    private DaftarPenyewa penyewa;

    // Relasi ke kamar
    @ManyToOne
    @JoinColumn(name = "kamar_id", nullable = false)
    private DaftarKamar kamar;

    @Column(nullable = false)
    private BigDecimal jumlah;

    @Column(nullable = false)
    private String metode; // CASH / TRANSFER / QRIS

    @Column(nullable = false)
    private String status = "PENDING"; // PENDING / LUNAS / GAGAL

    @Column(name = "tanggal_bayar", nullable = false)
    private LocalDateTime tanggalBayar = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String keterangan;
}
