package fi.tuni.prog3.sudoku;

public class Sudoku {
    private char[][] grid;

    public Sudoku() {
        grid = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void set(int i, int j, char c) {
        if (!isValidIndex(i, j)) {
            System.out.println("Trying to access illegal cell (" + i + ", " + j + ")!");
            return;
        }

        if (!isValidCharacter(c)) {
            System.out.println("Trying to set illegal character " + c + " to (" + i + ", " + j + ")!");
            return;
        }

        grid[i][j] = c;
    }

    public boolean check() {
        for (int i = 0; i < 9; i++) {
            if (!isValidSet(grid[i])) {
                System.out.println("Row " + i + " has multiple " + findRecurringDigit(grid[i]) + "'s!");
                return false;
            }
        }

        for (int j = 0; j < 9; j++) {
            char[] column = new char[9];
            for (int i = 0; i < 9; i++) {
                column[i] = grid[i][j];
            }
            if (!isValidSet(column)) {
                System.out.println("Column " + j + " has multiple " + findRecurringDigit(column) + "'s!");
                return false;
            }
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                char[] block = new char[9];
                int k = 0;
                for (int x = i; x < i + 3; x++) {
                    for (int y = j; y < j + 3; y++) {
                        block[k++] = grid[x][y];
                    }
                }
                if (!isValidSet(block)) {
                    System.out.println("Block at (" + i + ", " + j + ") has multiple " +
                            findRecurringDigit(block) + "'s!");
                    return false;
                }
            }
        }

        return true;
    }

    public void print() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("#####################################");
            } else {
                System.out.println("#---+---+---#---+---+---#---+---+---#");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print("# ");
                } else {
                    System.out.print("| ");
                }
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("#");
            System.out.println();
        }
        System.out.println("#####################################");
    }

    private boolean isValidIndex(int i, int j) {
        return i >= 0 && i < 9 && j >= 0 && j < 9;
    }

    private boolean isValidCharacter(char c) {
        return c == ' ' || (c >= '1' && c <= '9');
    }

    private boolean isValidSet(char[] set) {
        boolean[] seen = new boolean[10];
        for (char c : set) {
            if (c == ' ')
                continue;
            int digit = c - '0';
            if (seen[digit])
                return false;
            seen[digit] = true;
        }
        return true;
    }

    private char findRecurringDigit(char[] set) {
        boolean[] seen = new boolean[10];
        for (char c : set) {
            if (c == ' ')
                continue;
            int digit = c - '0';
            if (seen[digit])
                return c;
            seen[digit] = true;
        }
        return ' ';
    }
}