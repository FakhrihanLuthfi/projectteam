package com.example.kostease.service;

import com.example.kostease.dto.KamarRequest;
import com.example.kostease.entity.DaftarKamar;
import com.example.kostease.repository.DaftarKamarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KamarService {

    private final DaftarKamarRepository daftarKamarRepository;

    public KamarService(DaftarKamarRepository daftarKamarRepository) {
        this.daftarKamarRepository = daftarKamarRepository;
    }

    public Map<String, Object> create(KamarRequest req) {
        DaftarKamar kamar = new DaftarKamar();
        kamar.setNomor(req.getNomorKamar());
        kamar.setTipe(req.getTipeKamar());
        kamar.setHarga(req.getHarga());
        kamar.setStatus(req.getStatus());
        kamar.setGambar(req.getGambar());
        daftarKamarRepository.save(kamar);

        return Map.of("message", "DaftarKamar berhasil dibuat", "kamar", kamar);
    }

    public List<DaftarKamar> getAll() {
        return daftarKamarRepository.findAll();
    }

    public DaftarKamar getById(Long id) {
        return daftarKamarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DaftarKamar tidak ditemukan"));
    }

    public Map<String, Object> update(Long id, KamarRequest req) {
        DaftarKamar kamar = getById(id);
        kamar.setNomor(req.getNomorKamar());
        kamar.setTipe(req.getTipeKamar());
        kamar.setHarga(req.getHarga());
        kamar.setStatus(req.getStatus());
        kamar.setGambar(req.getGambar());
        daftarKamarRepository.save(kamar);

        return Map.of("message", "DaftarKamar berhasil diupdate", "kamar", kamar);
    }

    public Map<String, Object> delete(Long id) {
        DaftarKamar kamar = getById(id);
        daftarKamarRepository.delete(kamar);
        return Map.of("message", "DaftarKamar berhasil dihapus");
    }
}
