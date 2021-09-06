package parsers;

import validators.Validator;
import java.util.Arrays;

public class IntegerParser implements Parser<Integer> {
    private final String DELIMITER_REGEX = "//.\n";
    private final String DEFAULT_DELIMITERS = "[,\n]";
    private final Validator validator;

    public IntegerParser() {
        validator = new Validator();
    }

    public Integer[] parse(String stringToParse) {
        String delimiter = DEFAULT_DELIMITERS;
        // If there is a prefix for a custom delimiter, read it then remove it
        if (hasCustomDelimiter(stringToParse)) {
            delimiter = parseDelimiter(stringToParse);
            stringToParse = removeHead(stringToParse);
        }
        validator.checkDelimiter(delimiter);

        String[] numberString = parseBody(stringToParse, delimiter);
        validator.checkBody(numberString);

        Integer[] numbers = parseNumbers(numberString);
        validator.checkNegatives(numbers);

        return numbers;
    }

    /**
     * Finds the delimiter at the expected position from DELIMITER_REGEX.
     * Multiple custom delimiters are not supported.
     */
    public String parseDelimiter(String stringToParse) {
        int delimiterIndex = DELIMITER_REGEX.indexOf(".");
        String delimiter = String.valueOf(stringToParse.charAt(delimiterIndex));
        removeHead(stringToParse);
        return delimiter;
    }

    /**
     * Matches any chain starting with DELIMITER_REGEX.
     */
    public boolean hasCustomDelimiter(String stringToParse) {
        String regexIfDelimiter = DELIMITER_REGEX + ".*$";
        return stringToParse.matches(regexIfDelimiter);
    }

    public String[] parseBody(String stringToParse, String delimiter) {
        return Arrays.stream(stringToParse.split(delimiter))
                .toArray(String[]::new);
    }

    public Integer[] parseNumbers(String[] numberString) {
        return Arrays.stream(numberString)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }

    /**
     * Removes the delimiter prefix using the regex's last character.
     */
    public String removeHead(String stringToParse) {
        String whereToSplit = String.valueOf(DELIMITER_REGEX
                .charAt(DELIMITER_REGEX.length() - 1));
        return stringToParse.split(whereToSplit)[1];
    }
}
