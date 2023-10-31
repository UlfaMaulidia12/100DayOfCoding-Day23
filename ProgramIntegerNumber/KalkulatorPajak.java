package ProgramIntegerNumber;

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
            double pendapatanTahunan = getInput(input, "Masukkan Pendapatan Tahunan: $");
            double tarifPajak = getInput(input, "Masukkan Tarif Pajak Tahunan (%): ");

            if (tarifPajak < 0 || tarifPajak > 100) {
                System.out.println("Tarif pajak harus berada antara 0% dan 100%.");
                continue;
            }

            double pajakTahunan = hitungPajak(pendapatanTahunan, tarifPajak);
            double pajakBulanan = pajakTahunan / 12;

            System.out.println("\n=== Hasil Perhitungan Pajak ===");
            tampilkanHasil("Pendapatan Tahunan", pendapatanTahunan);
            tampilkanHasil("Tarif Pajak Tahunan", tarifPajak);
            tampilkanHasil("Pajak Tahunan", pajakTahunan);
            tampilkanHasil("Pajak Bulanan", pajakBulanan);

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
                nilai = input.nextDouble();
                if (nilai >= 0) {
                    return nilai;
                } else {
                    System.out.println("Pendapatan tahunan harus lebih besar dari atau sama dengan nol.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harap masukkan angka yang valid.");
                input.next(); // Hapus input yang tidak valid.
            }
        }
    }

    private static double hitungPajak(double pendapatan, double tarif) {
        return pendapatan * tarif / 100;
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
