package com.example.kostease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "daftar_penyewa")
public class DaftarPenyewa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @Column(name = "no_hp", nullable = false)
    private String noHp;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String alamat;

    // Relasi ke tabel user
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Relasi ke kamar
    @ManyToOne
    @JoinColumn(name = "kamar_id", referencedColumnName = "id")
    private DaftarKamar kamar;

    @Column(name = "tanggal_masuk", nullable = false)
    private LocalDate tanggalMasuk;

    @Column(name = "tanggal_keluar")
    private LocalDate tanggalKeluar;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
