package fi.tuni.prog3.sevenzipsearch;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SevenZipSearch {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt the user for the 7z file name and search word
            System.out.println("File:");
            String fileName = scanner.nextLine();
            System.out.println("Query:");
            String searchWord = scanner.nextLine();
            System.out.println();

            try (SevenZFile sevenZFile = new SevenZFile(new File(fileName))) {
                SevenZArchiveEntry entry;
                while ((entry = sevenZFile.getNextEntry()) != null) {
                    String name = entry.getName();

                    // Check if the entry is a text file
                    if (name.endsWith(".txt")) {
                        System.out.println(name);
                        searchAndPrintInTextFile(sevenZFile, entry, searchWord);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading the 7z file: " + e.getMessage());
            }
        }
    }

    private static void searchAndPrintInTextFile(SevenZFile sevenZFile, SevenZArchiveEntry entry, String searchWord) throws IOException {
        InputStream input = sevenZFile.getInputStream(entry);
        try (Scanner scanner = new Scanner(input)) {
            int lineNumber = 1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (containsCaseInsensitive(line, searchWord)) {
                    // Print the line with the search word in uppercase
                    String modifiedLine = highlightWord(line, searchWord);
                    System.out.println(lineNumber + ": " + modifiedLine);
                }
                lineNumber++;
            }
            System.out.println(); // Extra newline after each entry
        }
    }

    private static boolean containsCaseInsensitive(String line, String searchWord) {
        return Pattern.compile(Pattern.quote(searchWord), Pattern.CASE_INSENSITIVE).matcher(line).find();
    }

    private static String highlightWord(String line, String searchWord) {
        return line.replaceAll("(?i)" + Pattern.quote(searchWord), searchWord.toUpperCase());
    }
}
