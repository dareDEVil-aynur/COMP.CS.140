package fi.tuni.prog3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Parameters:");

        List<String> parameters = new ArrayList<>();
        String input;
        while (!(input = scanner.nextLine()).isEmpty()) {
            parameters.add(input);
        }

        Collections.sort(parameters);

        int maxNumberLength = String.valueOf(parameters.size()).length();
        int maxParamLength = parameters.stream().map(String::length).max(Integer::compare).orElse(0);

        String rowFormat = "# %" + maxNumberLength + "d | %-"
                + maxParamLength + "s #\n";

        String border = String.format("#%s#", "-".repeat(maxNumberLength + maxParamLength + 3));

        System.out.println(border.replace("-", "#"));

        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) {
                System.out.println(border.replace("#", "+"));
            }
            System.out.printf(rowFormat, i + 1, parameters.get(i));
        }

        System.out.println(border.replace("-", "#"));

        scanner.close();
    }
}
