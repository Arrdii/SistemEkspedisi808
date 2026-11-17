package com.mycompany.sistemekspedisi808;

import java.io.Serializable;

public class Transaksi implements Serializable {
    private Pengirim pengirim;
    private LayananPaket layanan;
    private int jumlah;

    public Transaksi(Pengirim pengirim, LayananPaket layanan, int jumlah) {
        this.pengirim = pengirim;
        this.layanan = layanan;
        this.jumlah = jumlah;
    }

    public Pengirim getPengirim() {
        return pengirim;
    }

    public LayananPaket getLayanan() {
        return layanan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String tampilkanTransaksi() {
        return pengirim.getKode() + " - "
                + layanan.getKode() + " - " + jumlah;
    }
}
