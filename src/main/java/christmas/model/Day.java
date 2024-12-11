package christmas.model;

import christmas.exception.CustomIllegalArgumentException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Day {

    private static List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);

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

    public int discountSpecialDay() {
        if (specialDays.contains(day)) {
            return 1000;
        }

        return 0;
    }

    public int dDayDiscount() {
        if (day >= 1 && day <= 25) {
            return 1000 + (day - 1) * 100;
        }

        return 0;
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.of(2023, 12, this.day));

        return dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY);
    }


    @Override
    public String toString() {
        return String.valueOf(day);
    }
}
