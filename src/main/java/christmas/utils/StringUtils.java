package christmas.utils;


import christmas.exception.CustomIllegalArgumentException;
import christmas.model.ErrorMessage;
import java.text.DecimalFormat;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class StringUtils {

    private static final String OR = "|";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");


    private StringUtils() {
    }

    private static void validateIsNumeric(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    private static void validateRange(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }


    public static String[] split(String regex, String input, Integer fieldCount, ErrorMessage errorMessage) {
        validateInput(regex, input, fieldCount, errorMessage);
        return input.split(regex);
    }

    private static void validateInput(String regex, String input, Integer fieldCount, ErrorMessage errorMessage) {
        if (regex == null || input == null || input.isBlank() || input.endsWith(regex)) {
            throw new CustomIllegalArgumentException(errorMessage);
        }

        String[] split = input.split(regex);
        if (fieldCount != null && split.length != fieldCount) {
            throw new CustomIllegalArgumentException(errorMessage);
        }
    }


    public static String regexSeparators(List<String> separators) {
        StringJoiner regex = new StringJoiner(OR);

        for (String separator : separators) {
            regex.add(Pattern.quote(separator));
        }

        return regex.toString();
    }


    public static String numberFormat(long number) {
        DecimalFormat format = new DecimalFormat("#,###");

        return format.format(number);
    }


}
