package ProgramLiterals;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class PajakKode {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char ulangi;

        do {
            System.out.println("=== Kalkulator Kode Pajak ===");
            int jumlahPajak = getInput(input, "Masukkan jumlah pajak: ", 1, 10);
            Pajak[] pajakList = new Pajak[jumlahPajak];

            for (int i = 0; i < jumlahPajak; i++) {
                pajakList[i] = getInputAndCreatePajak(input, "Masukkan Pajak " + (i + 1));
            }

            System.out.println("\n=== Hasil Perhitungan Pajak ===");

            for (Pajak pajak : pajakList) {
                displayPajak(pajak);
                calculateAndDisplayTax(pajak);
            }

            ulangi = getYesNoInput(input, "Hitung pajak lagi? (Y/N): ");
        } while (ulangi == 'Y' || ulangi == 'y');

        input.close();
        System.out.println("Terima kasih telah menggunakan kalkulator pajak!");
    }

    private static int getInput(Scanner input, String message, int min, int max) {
        int value;

        while (true) {
            try {
                System.out.print(message);
                value = input.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Masukkan angka antara " + min + " dan " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harap masukkan angka yang valid.");
                input.next(); // Hapus input yang tidak valid.
            }
        }
    }

    private static Pajak getInputAndCreatePajak(Scanner input, String message) {
        int kodePajak = getInput(input, message + " - Masukkan Kode Pajak (1-999): ", 1, 999);
        int persentasePajak = getInput(input, message + " - Masukkan Persentase Pajak (%): ", 0, 100);
        return new Pajak(kodePajak, persentasePajak);
    }

    private static void displayPajak(Pajak pajak) {
        System.out.println("Kode Pajak: " + pajak.getKodePajak());
        System.out.println("Persentase Pajak: " + pajak.getPersentasePajak() + "%");
    }

    private static void calculateAndDisplayTax(Pajak pajak) {
        double calculatedTax = pajak.calculatePajak();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        System.out.println("Pajak yang harus dibayar: $" + decimalFormat.format(calculatedTax));
    }

    private static char getYesNoInput(Scanner input, String message) {
        char response;

        while (true) {
            System.out.print(message);
            response = input.next().charAt(0);
            if (response == 'Y' || response == 'y' || response == 'N' || response == 'n') {
                return response;
            } else {
                System.out.println("Pilihan tidak valid. Harap masukkan 'Y' atau 'N'.");
            }
        }
    }
}

class Pajak {
    private int kodePajak;
    private int persentasePajak;

    public Pajak(int kodePajak, int persentasePajak) {
        this.kodePajak = kodePajak;
        this.persentasePajak = persentasePajak;
    }

    public int getKodePajak() {
        return kodePajak;
    }

    public int getPersentasePajak() {
        return persentasePajak;
    }

    public double calculatePajak() {
        return (double) kodePajak * persentasePajak / 100;
    }
}
