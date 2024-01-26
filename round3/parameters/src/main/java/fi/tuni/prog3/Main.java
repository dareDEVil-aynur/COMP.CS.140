package fi.tuni.prog3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> parameters = new ArrayList<>();

        System.out.println("Parameters:");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                scanner.close();
                break;
            }
            parameters.add(input);
        }

        Collections.sort(parameters);

        int col1Width = String.valueOf(parameters.size()).length() + 2;
        int col2Width = parameters.stream().mapToInt(String::length).max().orElse(0) + 2;

        createBorder(col1Width, col2Width, "top");

        for (int i = 0; i < parameters.size(); i++) {
            System.out.format("# %" + (col1Width - 2) + "d | %-" + (col2Width - 2) + "s #\n", i + 1, parameters.get(i));
            if(i != parameters.size() - 1) {
                createBorder(col1Width, col2Width, "middle");
            }
        }

        createBorder(col1Width, col2Width, "bottom");
    }

    private static void createBorder(int col1Width, int col2Width, String type) {
        System.out.print("#");
        char midChar = type.equals("middle") ? '-' : '#';
        for (int i = 0; i < col1Width; i++) {
            System.out.print(midChar);
        }
        System.out.print(type.equals("middle") ? "+" : "#");
        for (int i = 0; i < col2Width; i++) {
            System.out.print(midChar);
        }
        System.out.println("#");
    }
}
