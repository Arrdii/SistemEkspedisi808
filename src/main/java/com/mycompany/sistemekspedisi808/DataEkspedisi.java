package com.mycompany.sistemekspedisi808;

import java.io.Serializable;

public abstract class DataEkspedisi implements Serializable {
    private String kode;
    private String nama;

    public DataEkspedisi(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public abstract String tampilkanInfo();
}
