package com.ravi.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.product.entity.JenisProduk;
import com.ravi.product.repository.JenisProdukRepository;

@Service
public class JenisProdukService {

    @Autowired
    private JenisProdukRepository JenisProdukRepository;

    public List<JenisProduk> getAllJenisProduk() {
        return JenisProdukRepository.findAll();

    }

    public JenisProduk getJenisProdukById(Long id) {
        return JenisProdukRepository.findById(id).orElse(null);
    }

    public JenisProduk saveJenisProduk(JenisProduk jenisProduk) {
        return JenisProdukRepository.save(jenisProduk);
    }

    public void deleteJenisProduk(Long id) {
        JenisProdukRepository.deleteById(id);
    }

    public JenisProduk updateJenisProduk(Long id, JenisProduk jenisProduk) {
        JenisProduk existing = JenisProdukRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setJenis(jenisProduk.getJenis());
            return JenisProdukRepository.save(existing);
        }
        return null;
    }
}
