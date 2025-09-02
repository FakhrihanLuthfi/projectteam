package com.example.kostease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "daftar_kamar")
public class DaftarKamar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomor_kamar", nullable = false, unique = true)
    private String nomor;       // disesuaikan dengan setter DTO

    @Column(name = "tipe_kamar", nullable = false)
    private String tipe;        // disesuaikan dengan setter DTO

    @Column(nullable = false)
    private java.math.BigDecimal harga;

    @Column(nullable = false)
    private String status = "TERSEDIA"; // TERSEDIA / TERISI

    @Column(name = "gambar")
    private String gambar;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
