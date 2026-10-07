package com.ravi.order.VO;
    
import java.util.Date;

import com.ravi.order.Entity.Order;

public class OrderResponseVO {
    private Long id;
    private Long produk_id;
    private Long pelanggan_id;
    private Date tgl_trans;
    private int jumlah;
    private Long total;

    private ProdukVO produk;
    private PelangganVO pelanggan;

    public OrderResponseVO(
            Order order,
            ProdukVO produk,
            PelangganVO pelanggan) {

        this.id = order.getId();
        this.produk_id = order.getProduk_id();
        this.pelanggan_id = order.getPelanggan_id();
        this.tgl_trans = order.getTgl_trans();
        this.jumlah = order.getJumlah();
        this.total = order.getTotal();

        this.produk = produk;
        this.pelanggan = pelanggan;
    }

    public Long getId() {
        return id;
    }

    public Long getProduk_id() {
        return produk_id;
    }

    public Long getPelanggan_id() {
        return pelanggan_id;
    }

    public Date getTgl_trans() {
        return tgl_trans;
    }

    public int getJumlah() {
        return jumlah;
    }

    public Long getTotal() {
        return total;
    }

    public ProdukVO getProduk() {
        return produk;
    }

    public PelangganVO getPelanggan() {
        return pelanggan;
    }
}

