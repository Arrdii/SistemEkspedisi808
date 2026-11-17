
package com.mycompany.sistemekspedisi808;

import java.io.*;
import java.util.*;

public class SistemEkspedisi808 implements Serializable {
    private String namaPerusahaan;
    private HashMap<String, Pengirim> daftarPengirim;
    private HashMap<String, LayananPaket> daftarLayanan;
    private HashMap<String, DataEkspedisi> daftarData;
    private ArrayList<Transaksi> daftarTransaksi;

    public SistemEkspedisi808(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
        daftarPengirim = new HashMap<>();
        daftarLayanan = new HashMap<>();
        daftarData = new HashMap<>();
        daftarTransaksi = new ArrayList<>();
    }

    public void tambahPengirim(String kode, String nama) {
        Pengirim pengirim = new Pengirim(kode, nama);
        daftarPengirim.put(kode, pengirim);
        daftarData.put(kode, pengirim);
    }

    public void tambahLayananPaket(String kode, String nama, int kuota) {
        LayananPaket layanan = new LayananPaket(kode, nama, kuota);
        daftarLayanan.put(kode, layanan);
        daftarData.put(kode, layanan);
    }

    private void prosesTransaksi(String baris) throws TransaksiException {
        try {
            String[] data = baris.split("\\.");

            String[] id = data[0].split(">");
            String[] kd = data[1].split(">");
            String[] jml = data[2].split(">");

            if (!id[0].equals("ID") || !kd[0].equals("KD")
                    || !jml[0].equals("JML")) {
                throw new TransaksiException("Format data tidak sesuai");
            }

            String kodePengirim = id[1];
            String kodeLayanan = kd[1];
            int jumlah = Integer.parseInt(jml[1]);

            Pengirim pengirim = daftarPengirim.get(kodePengirim);
            LayananPaket layanan = daftarLayanan.get(kodeLayanan);

            if (pengirim == null) {
                throw new TransaksiException("Pengirim tidak ditemukan: " + kodePengirim);
            }

            if (layanan == null) {
                throw new TransaksiException("Layanan tidak ditemukan: " + kodeLayanan);
            }

            layanan.kurangiKuota(jumlah);

            Transaksi transaksi = new Transaksi(pengirim, layanan, jumlah);
            daftarTransaksi.add(transaksi);

            System.out.println("Transaksi berhasil: "
                    + transaksi.tampilkanTransaksi());

        } catch (NumberFormatException e) {
            throw new TransaksiException("Jumlah harus berupa angka");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TransaksiException("Format data tidak lengkap");
        }
    }

    public void jalankanTransaksiDariFile(String namaFile) throws Exception {
        try (Scanner scanner = new Scanner(new File(namaFile))) {
            while (scanner.hasNextLine()) {
                String baris = scanner.nextLine();

                if (!baris.equals("")) {
                    try {
                        prosesTransaksi(baris);
                    } catch (TransaksiException e) {
                        System.out.println("Transaksi gagal: " + e.getMessage());
                    }
                }
            }
        }
    }

    public void simpanLaporanAkhir() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream("snapshot.obj"));

            output.writeObject(this);
            output.close();

            System.out.println("Serialization berhasil disimpan.");

        } catch (IOException e) {
            System.out.println("Gagal menyimpan serialization: " + e.getMessage());
        }

        try {
            FileWriter writer = new FileWriter("laporan.txt");

            TreeSet<String> daftarKode = new TreeSet<>(daftarData.keySet());

            for (String kode : daftarKode) {
                writer.write(kode + "\n");
            }

            writer.close();

            System.out.println("Laporan berhasil disimpan.");

        } catch (IOException e) {
            System.out.println("Gagal membuat laporan: " + e.getMessage());
        }
    }
}
