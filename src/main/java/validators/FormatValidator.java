package validators;

import exceptions.BadDelimiterException;
import exceptions.BadFormatException;
import exceptions.NegativeNumberException;

import java.util.List;
import java.util.stream.Collectors;

public class FormatValidator {
    // Digits cannot be delimiters
    // - is needed to catch negatives and throw a specific exception
    // / and \n are part of the custom delimiter prefix
    private final String FORBIDDEN_DELIMITERS = "[0-9-/\n]";
    // Matches any integer
    private final String INTEGER_REGEX = "-?[0-9]+";

    /**
     * @throws BadDelimiterException
     */
    public void checkDelimiter(String delimiter) {
        if (delimiter.matches(FORBIDDEN_DELIMITERS)) {
            throw new BadDelimiterException(delimiter);
        }
    }

    /**
     * Checks if all strings can be parsed to integers (negatives are handled elsewhere).
     * @throws BadFormatException
     */
    public void checkBody(List<String> body) {
        for (String s:body) {
            if (!s.matches(INTEGER_REGEX)) {
                throw new BadFormatException("'" + s + "' cannot be parse as an integer");
            }
        }
    }

    /**
     * @throws NegativeNumberException
     */
    public void checkNegatives(List<Integer> numbers) {
        List<Integer> negatives = numbers.stream()
                .filter(n -> n < 0)
                .collect(Collectors.toList());
        if (!negatives.isEmpty()) {
            throw new NegativeNumberException(negatives);
        }
    }
}
