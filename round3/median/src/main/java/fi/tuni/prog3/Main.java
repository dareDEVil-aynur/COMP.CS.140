package fi.tuni.prog3;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter numbers: ");
        String[] inputs = scanner.nextLine().split("\\s+");
        double[] numbers = Arrays.stream(inputs)
                .mapToDouble(Double::parseDouble)
                .sorted()
                .toArray();

        double median;
        if (numbers.length % 2 == 0) {
            median = (numbers[numbers.length / 2] + numbers[numbers.length / 2 - 1]) / 2;
        } else {
            median = numbers[numbers.length / 2];
        }

        System.out.println("Median: " + median);
        scanner.close();
    }
}
