package ProgramUnderscore;

import java.util.Scanner;

public class CalculatorProgram {
    public static void main(String[] args) {
        char ulangi;

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("=== Calculator Program ===");

            double angka1 = getInput(input, "Masukkan angka pertama: ");
            double angka2 = getInput(input, "Masukkan angka kedua: ");

            System.out.println("Pilihan operasi matematika:");
            System.out.println("1. Penjumlahan");
            System.out.println("2. Pengurangan");
            System.out.println("3. Perkalian");
            System.out.print("Pilih operasi (1/2/3): ");
            int pilihan = getOperationChoice(input);

            if (pilihan == 1) {
                double hasil = angka1 + angka2;
                System.out.println("Hasil penjumlahan: " + hasil);
            } else if (pilihan == 2) {
                double hasil = angka1 - angka2;
                System.out.println("Hasil pengurangan: " + hasil);
            } else if (pilihan == 3) {
                double hasil = angka1 * angka2;
                System.out.println("Hasil perkalian: " + hasil);
            } else {
                System.out.println("Operasi tidak valid. Silakan pilih 1, 2, atau 3.");
            }

            ulangi = getYesNoInput(input, "Ingin melakukan perhitungan lagi? (Y/N): ");
        } while (ulangi == 'Y' || ulangi == 'y');

        input.close();
        System.out.println("Terima kasih!");
    }

    private static double getInput(Scanner input, String message) {
        double angka = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(message);
                angka = input.nextDouble();
                valid = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Input tidak valid. Masukkan angka yang benar.");
                input.nextLine(); // Bersihkan buffer input
            }
        }

        return angka;
    }

    private static int getOperationChoice(Scanner input) {
        int choice = 0;
        boolean validChoice = false;

        while (!validChoice) {
            choice = input.nextInt();
            if (choice >= 1 && choice <= 3) {
                validChoice = true;
            } else {
                System.out.println("Pilihan operasi tidak valid. Silakan pilih 1, 2, atau 3.");
            }
        }

        return choice;
    }

    private static char getYesNoInput(Scanner input, String message) {
        char response;

        while (true) {
            System.out.print(message);
            response = input.next().charAt(0);
            if (response == 'Y' || response == 'y' || response == 'N' || response == 'n') {
                break;
            } else {
                System.out.println("Pilihan tidak valid. Masukkan 'Y' atau 'N'.");
            }
        }

        return response;
    }
}
