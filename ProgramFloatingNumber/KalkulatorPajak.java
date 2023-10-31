package ProgramFloatingNumber;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;

public class KalkulatorPajak {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char ulangiPerhitungan = 0;

        do {
            System.out.println("=== Kalkulator Pajak ===");
            double pendapatanTahunan = getInput(input, "Masukkan Pendapatan Tahunan\t: $");
            double tarifPajak = getInput(input, "Masukkan Tarif Pajak Tahunan(%): ");
            
            if (tarifPajak < 0 || tarifPajak > 100) {
                System.out.println("Tarif pajak harus berada antara 0% dan 100%.");
                continue;
            }
            
            // Menampilkan menu pilihan jenis pajak
            System.out.println("Pilih Jenis Pajak:");
            System.out.println("1. Pajak Penghasilan ");
            System.out.println("2. Pajak Penjualan ");
            System.out.print("Masukkan nomor jenis pajak yang ingin dihitung: ");
            int jenisPajak = input.nextInt();
            
            double pajakTahunan = hitungPajak(pendapatanTahunan, tarifPajak, jenisPajak);
            
            System.out.println("\n=== Hasil Perhitungan Pajak ===");
            tampilkanHasil("Pendapatan Tahunan", pendapatanTahunan);
            tampilkanHasil("Tarif Pajak Tahunan", tarifPajak);
            tampilkanHasil("Pajak Tahunan", pajakTahunan);

            ulangiPerhitungan = getYesNoInput(input, "Hitung pajak lagi? (Y/N): ");
        } while (ulangiPerhitungan == 'y' || ulangiPerhitungan == 'Y');

        input.close();
        System.out.println("Terima kasih telah menggunakan kalkulator pajak!");
    }

    private static double getInput(Scanner input, String pesan) {
        double nilai;

        while (true) {
            try {
                System.out.print(pesan);
                while (!input.hasNextDouble()) {
                    System.out.println("Input tidak valid. Harap masukkan angka yang benar.");
                    System.out.print(pesan);
                    input.next();
                }
                nilai = input.nextDouble();
                if (nilai >= 0) {
                    return nilai;
                } else {
                    System.out.println("Pendapatan tahunan harus lebih besar dari atau sama dengan nol.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harap masukkan angka yang benar.");
                input.next(); // Hapus input yang tidak valid.
            }
        }
    }

    private static double hitungPajak(double pendapatan, double tarif, int jenisPajak) {
        if (jenisPajak == 1) {
            // Hitung Pajak Penghasilan (Income Tax)
            return pendapatan * tarif / 100;
        } else if (jenisPajak == 2) {
            // Hitung Pajak Penjualan (Sales Tax)
            return pendapatan * (tarif / 100) * 0.08; // Anggap 8% sebagai tarif Pajak Penjualan
        } else {
            System.out.println("Jenis pajak tidak valid.");
            return 0;
        }
    }

    private static void tampilkanHasil(String label, double nilai) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        String nilaiTerformat = currencyFormatter.format(nilai);
        System.out.printf("%-20s: %s%n", label, nilaiTerformat);
    }

    private static char getYesNoInput(Scanner input, String pesan) {
        char respons;

        while (true) {
            System.out.print(pesan);
            respons = input.next().charAt(0);
            if (respons == 'Y' || respons == 'y' || respons == 'N' || respons == 'n') {
                return respons;
            } else {
                System.out.println("Pilihan tidak valid. Harap masukkan 'Y' atau 'N'.");
            }
        }
    }
}
