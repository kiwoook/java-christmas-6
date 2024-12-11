package christmas.model;

import christmas.exception.CustomIllegalArgumentException;

public class Day {

    private final int day;

    public Day(int day) {
        this.day = day;
    }

    public static Day from(String input) {
        int day = parseDay(input);
        validDay(day);

        return new Day(day);
    }

    private static void validDay(int day) {
        if (day < 1 || day > 31) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_DAY);
        }
    }

    private static int parseDay(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_DAY);
        }
    }
}
