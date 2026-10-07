package com.ravi.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.product.entity.JenisProduk;
import com.ravi.product.entity.Produk;
import com.ravi.product.service.JenisProdukService;
import com.ravi.product.service.ProdukService;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {

    @Autowired
    private ProdukService produkService;
    @Autowired
    private JenisProdukService jenisProdukService;

    @GetMapping
    public List<Produk> getAllProduk(@RequestParam(value = "idjenis", required = false) Long idjenis) {
        if (idjenis != null) {
            return produkService.getAllBarangByIdJenis(idjenis);
        }
        return produkService.getAllProduk();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produk> getProdukById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(produkService.getProdukById(id));
    }

    @PostMapping
    public ResponseEntity<Produk> createProduk(@RequestBody Produk produk) {
        return ResponseEntity.ok(produkService.saveProduk(produk));
    }


     @PutMapping("/{id}")
    public ResponseEntity<Produk> updateProduk(@PathVariable Long id, @RequestBody Produk produk) {
        Produk updated = produkService.updateProduk(id, produk);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduk(@PathVariable Long id) {
        produkService.deleteProduk(id);
        return ResponseEntity.noContent().build();
    }

    // --- ENDPOINT JENIS PRODUK ---

    @GetMapping("/jenis")
    public List<JenisProduk> getAllJenisProduk() {
        return jenisProdukService.getAllJenisProduk();
    }

    @GetMapping("/jenis/{id}")
    public ResponseEntity<JenisProduk> getJenisProdukById(@PathVariable Long id) {
        return ResponseEntity.ok(jenisProdukService.getJenisProdukById(id));
    }

    @PostMapping("/jenis")
    public ResponseEntity<JenisProduk> createJenisProduk(@RequestBody JenisProduk jenisProduk) {
        return ResponseEntity.ok(jenisProdukService.saveJenisProduk(jenisProduk));
    }

    @PutMapping("/jenis/{id}")
    public ResponseEntity<JenisProduk> updateJenisProduk(
            @PathVariable Long id, @RequestBody JenisProduk jenisProduk) {
        JenisProduk updated = jenisProdukService.updateJenisProduk(id, jenisProduk);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/jenis/{id}")
    public ResponseEntity<Void> deleteJenisProduk(@PathVariable Long id) {
        jenisProdukService.deleteJenisProduk(id);
        return ResponseEntity.noContent().build();
    }
}
