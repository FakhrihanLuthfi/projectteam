package com.example.kostease.service;

import com.example.kostease.dto.PenyewaRequest;
import com.example.kostease.entity.DaftarKamar;
import com.example.kostease.entity.DaftarPenyewa;
import com.example.kostease.entity.User;
import com.example.kostease.repository.DaftarKamarRepository;
import com.example.kostease.repository.DaftarPenyewaRepository;
import com.example.kostease.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PenyewaService {

    private final DaftarPenyewaRepository penyewaRepository;
    private final UserRepository userRepository;
    private final DaftarKamarRepository kamarRepository;

    public PenyewaService(
            DaftarPenyewaRepository penyewaRepository,
            UserRepository userRepository,
            DaftarKamarRepository kamarRepository
    ) {
        this.penyewaRepository = penyewaRepository;
        this.userRepository = userRepository;
        this.kamarRepository = kamarRepository;
    }

    public Map<String, Object> create(PenyewaRequest req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        DaftarKamar kamar = kamarRepository.findById(req.getKamarId())
                .orElseThrow(() -> new RuntimeException("Kamar tidak ditemukan"));

        DaftarPenyewa penyewa = new DaftarPenyewa();
        penyewa.setNamaLengkap(req.getNamaLengkap());
        penyewa.setEmail(req.getEmail());
        penyewa.setNoHp(req.getNoHp());
        penyewa.setAlamat(req.getAlamat());
        penyewa.setUser(user);
        penyewa.setKamar(kamar);
        penyewa.setTanggalMasuk(req.getTanggalMasuk());
        penyewa.setTanggalKeluar(req.getTanggalKeluar());

        penyewaRepository.save(penyewa);

        return Map.of("message", "Penyewa berhasil dibuat", "penyewa", penyewa);
    }

    public List<DaftarPenyewa> getAll() {
        return penyewaRepository.findAll();
    }

    public DaftarPenyewa getById(Long id) {
        return penyewaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Penyewa tidak ditemukan"));
    }

    public Map<String, Object> update(Long id, PenyewaRequest req) {
        DaftarPenyewa penyewa = getById(id);

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        DaftarKamar kamar = kamarRepository.findById(req.getKamarId())
                .orElseThrow(() -> new RuntimeException("Kamar tidak ditemukan"));

        penyewa.setNamaLengkap(req.getNamaLengkap());
        penyewa.setEmail(req.getEmail());
        penyewa.setNoHp(req.getNoHp());
        penyewa.setAlamat(req.getAlamat());
        penyewa.setUser(user);
        penyewa.setKamar(kamar);
        penyewa.setTanggalMasuk(req.getTanggalMasuk());
        penyewa.setTanggalKeluar(req.getTanggalKeluar());

        penyewaRepository.save(penyewa);

        return Map.of("message", "Penyewa berhasil diupdate", "penyewa", penyewa);
    }

    public Map<String, Object> delete(Long id) {
        DaftarPenyewa penyewa = getById(id);
        penyewaRepository.delete(penyewa);
        return Map.of("message", "Penyewa berhasil dihapus");
    }
}
