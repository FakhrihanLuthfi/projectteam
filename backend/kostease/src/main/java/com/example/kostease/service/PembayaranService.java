package com.example.kostease.service;

import com.example.kostease.dto.PembayaranRequest;
import com.example.kostease.entity.DaftarKamar;
import com.example.kostease.entity.DaftarPenyewa;
import com.example.kostease.entity.Pembayaran;
import com.example.kostease.repository.DaftarKamarRepository;
import com.example.kostease.repository.DaftarPenyewaRepository;
import com.example.kostease.repository.PembayaranRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PembayaranService {
    private final PembayaranRepository pembayaranRepository;
    private final DaftarPenyewaRepository penyewaRepository;
    private final DaftarKamarRepository kamarRepository;

    public PembayaranService(
            PembayaranRepository pembayaranRepository,
            DaftarPenyewaRepository penyewaRepository,
            DaftarKamarRepository kamarRepository
    ) {
        this.pembayaranRepository = pembayaranRepository;
        this.penyewaRepository = penyewaRepository;
        this.kamarRepository = kamarRepository;
    }

    public Map<String, Object> create(PembayaranRequest req) {
        DaftarPenyewa penyewa = penyewaRepository.findById(req.getPenyewaId())
                .orElseThrow(() -> new RuntimeException("Penyewa tidak ditemukan"));

        DaftarKamar kamar = kamarRepository.findById(req.getKamarId())
                .orElseThrow(() -> new RuntimeException("Kamar tidak ditemukan"));

        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setPenyewa(penyewa);
        pembayaran.setKamar(kamar);
        pembayaran.setJumlah(req.getJumlah());
        pembayaran.setMetode(req.getMetode());
        pembayaran.setStatus(req.getStatus());
        pembayaran.setKeterangan(req.getKeterangan());

        pembayaranRepository.save(pembayaran);

        return Map.of("message", "Pembayaran berhasil dibuat", "pembayaran", pembayaran);
    }

    public List<Pembayaran> getAll() {
        return pembayaranRepository.findAll();
    }

    public Pembayaran getById(Long id) {
        return pembayaranRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pembayaran tidak ditemukan"));
    }

    public Map<String, Object> update(Long id, PembayaranRequest req) {
        Pembayaran pembayaran = getById(id);

        DaftarPenyewa penyewa = penyewaRepository.findById(req.getPenyewaId())
                .orElseThrow(() -> new RuntimeException("Penyewa tidak ditemukan"));

        DaftarKamar kamar = kamarRepository.findById(req.getKamarId())
                .orElseThrow(() -> new RuntimeException("Kamar tidak ditemukan"));

        pembayaran.setPenyewa(penyewa);
        pembayaran.setKamar(kamar);
        pembayaran.setJumlah(req.getJumlah());
        pembayaran.setMetode(req.getMetode());
        pembayaran.setStatus(req.getStatus());
        pembayaran.setKeterangan(req.getKeterangan());

        pembayaranRepository.save(pembayaran);

        return Map.of("message", "Pembayaran berhasil diupdate", "pembayaran", pembayaran);
    }

    public Map<String, Object> delete(Long id) {
        Pembayaran pembayaran = getById(id);
        pembayaranRepository.delete(pembayaran);
        return Map.of("message", "Pembayaran berhasil dihapus");
    }
}
