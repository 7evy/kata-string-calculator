package validators;

import exceptions.BadDelimiterException;
import exceptions.BadFormatException;
import exceptions.NegativeNumberException;
import java.util.Arrays;

public class Validator {
    private final String FORBIDDEN_DELIMITERS = "[0-9-\n]";
    private final String INTEGER_REGEX = "-?[0-9]+";

    public void checkDelimiter(String delimiter) {
        if (delimiter.matches(FORBIDDEN_DELIMITERS)) {
            throw new BadDelimiterException(delimiter);
        }
    }

    /**
     * Checks if all strings can be parsed to integers (negatives are handled elsewhere).
     */
    public void checkBody(String[] body) {
        for (String s:body) {
            if (!s.matches(INTEGER_REGEX)) {
                throw new BadFormatException("'" + s + "' cannot be parse as an integer");
            }
        }
    }

    public void checkNegatives(Integer[] numbers) {
        Integer[] negatives = Arrays.stream(numbers)
                .filter(n -> n < 0)
                .toArray(Integer[]::new);
        if (negatives.length > 0) {
            throw new NegativeNumberException(negatives);
        }
    }
}
