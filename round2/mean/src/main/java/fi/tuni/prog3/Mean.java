package fi.tuni.prog3;

import java.util.Scanner;

public class Mean {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter numbers: ");
            String line = scanner.nextLine();
            String[] numbers = line.split(" ");
            double sum = 0;
            for (String num : numbers) {
                sum += Double.parseDouble(num);
            }
            double mean = sum / numbers.length;
            System.out.println("Mean: " + mean);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }
}
