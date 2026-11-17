package com.mycompany.sistemekspedisi808;

public class MainEkspedisi {
    public static void main(String[] args) {
        SistemEkspedisi808 ekspedisi = new SistemEkspedisi808("Kargo Cepat");

        ekspedisi.tambahPengirim("ACT-01", "Kiki");
        ekspedisi.tambahPengirim("ACT-02", "Lina");

        ekspedisi.tambahLayananPaket("OBJ-A", "Kargo Udara Premium", 10);
        ekspedisi.tambahLayananPaket("OBJ-B", "Reguler Darat", 2);
        ekspedisi.tambahLayananPaket("OBJ-C", "Ekonomi Laut", 50);

        System.out.println("--- MEMULAI PEMBACAAN FILE TRANSAKSI ---");

        try {
            ekspedisi.jalankanTransaksiDariFile("input_transaksi.txt");
        } catch (Exception e) {
            System.out.println("[SISTEM FATAL ERROR] File gagal dibaca/diproses: "
                    + e.getMessage());
        }

        ekspedisi.simpanLaporanAkhir();
    }
}
