package ProgramTipeDataBoolean;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Pajak {
    private static final double TAX_RATE = 0.1; // Persentase pajak 10%
    private static final String CURRENCY_FORMAT = "###,###,###.00";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char ulangi;

        do {
            System.out.println("=== Kalkulator Pajak ===");
            double pendapatanTahunan = getInputDouble("Masukkan Pendapatan Tahunan: $");
            double batasPendapatan = getInputDouble("Masukkan Batas Pendapatan untuk Pajak: $");
            double pajakTahunan = calculateTax(pendapatanTahunan, batasPendapatan);

            System.out.println("=======================================");
            System.out.printf("Pendapatan Tahunan\t\t: %s%n", formatCurrency(pendapatanTahunan));
            System.out.printf("Batas Pendapatan untuk Pajak\t: %s%n", formatCurrency(batasPendapatan));
            System.out.println("=======================================");

            if (pajakTahunan > 0) {
                System.out.printf("Anda harus membayar pajak sebesar: %s%n", formatCurrency(pajakTahunan));
            } else {
                System.out.println("Anda tidak harus membayar pajak.");
            }

            ulangi = getYesNoInput("Hitung pajak lagi? (Y/N): ");
        } while (ulangi == 'Y' || ulangi == 'y');

        input.close();
        System.out.println("Terima kasih telah menggunakan kalkulator pajak!");
    }

    private static double getInputDouble(String message) {
        double value = 0;
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(message);
                value = input.nextDouble();
                if (value < 0) {
                    System.out.println("Input tidak valid. Harus lebih dari atau sama dengan nol.");
                } else {
                    return value;
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid. Masukkan angka desimal yang benar.");
                input.nextLine(); // Clear the invalid input.
            }
        }
    }

    private static double calculateTax(double pendapatanTahunan, double batasPendapatan) {
        double pajak = 0;
        if (pendapatanTahunan > batasPendapatan) {
            pajak = pendapatanTahunan * TAX_RATE;
        }
        return pajak;
    }

    private static String formatCurrency(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getNumberInstance(Locale.US);
        return currencyFormatter.format(amount);
    }

    private static char getYesNoInput(String message) {
        char response;
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print(message);
            response = input.next().charAt(0);
            if (response == 'Y' || response == 'y' || response == 'N' || response == 'n') {
                return response;
            } else {
                System.out.println("Pilihan tidak valid. Masukkan 'Y' atau 'N'.");
            }
        }
    }

    public Pajak(int kodePajak, int persentasePajak) {
        // Menginisialisasi objek Pajak
        // TODO: Implementasikan inisialisasi objek Pajak
    }

    public String getKodePajak() {
        // Mendapatkan kode pajak
        // TODO: Implementasikan kode untuk mendapatkan kode pajak
        return null;
    }

    public String getPersentasePajak() {
        // Mendapatkan persentase pajak
        // TODO: Implementasikan kode untuk mendapatkan persentase pajak
        return null;
    }
}
