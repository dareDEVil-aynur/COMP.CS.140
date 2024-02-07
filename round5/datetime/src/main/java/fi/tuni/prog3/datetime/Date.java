package fi.tuni.prog3.datetime;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) throws DateException {
        if (!isValidDate(year, month, day)) {
            throw new DateException("Illegal date " + day + "." + month + "." + year);
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return String.format("%02d.%02d.%d", day, month, year);
    }

    private boolean isValidDate(int year, int month, int day) {
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }

        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (isLeapYear(year)) {
            daysInMonth[1] = 29; // February has 29 days in a leap year
        }

        return day <= daysInMonth[month - 1];
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}