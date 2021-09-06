package validators;

import exceptions.BadDelimiterException;
import exceptions.BadFormatException;
import exceptions.NegativeNumberException;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
    public void checkBody(ArrayList<String> body) {
        for (String s:body) {
            if (!s.matches(INTEGER_REGEX)) {
                throw new BadFormatException("'" + s + "' cannot be parse as an integer");
            }
        }
    }

    public void checkNegatives(ArrayList<Integer> numbers) {
        ArrayList<Integer> negatives = numbers.stream()
                .filter(n -> n < 0)
                .collect(Collectors.toCollection(ArrayList<Integer>::new));
        if (!negatives.isEmpty()) {
            throw new NegativeNumberException(negatives);
        }
    }
}
