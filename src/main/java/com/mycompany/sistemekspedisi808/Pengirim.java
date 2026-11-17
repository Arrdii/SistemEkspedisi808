package com.mycompany.sistemekspedisi808;

public class Pengirim extends DataEkspedisi {

    public Pengirim(String kode, String nama) {
        super(kode, nama);
    }

    @Override
    public String tampilkanInfo() {
        return "Pengirim: " + getKode() + " - " + getNama();
    }
}
