package com.mycompany.sistemekspedisi808;

public class LayananPaket extends DataEkspedisi {
    private int kuota;

    public LayananPaket(String kode, String nama, int kuota) {
        super(kode, nama);
        this.kuota = kuota;
    }

    public int getKuota() {
        return kuota;
    }

    public void kurangiKuota(int jumlah) throws TransaksiException {
        if (jumlah <= 0) {
            throw new TransaksiException("Jumlah harus lebih dari 0");
        }

        if (jumlah > kuota) {
            throw new TransaksiException("Kuota layanan tidak mencukupi");
        }

        kuota -= jumlah;
    }

    @Override
    public String tampilkanInfo() {
        return "Layanan: " + getKode() + " - " + getNama()
                + " - Kuota: " + kuota;
    }
}
