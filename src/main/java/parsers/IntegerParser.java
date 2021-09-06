package parsers;

import validators.FormatValidator;

import java.util.List;
import java.util.stream.Collectors;

public class IntegerParser implements Parser<Integer> {
    // Syntax for custom delimiter
    private final String DELIMITER_REGEX = "//.\n";
    // Default if delimiter is not set
    private final String DEFAULT_DELIMITERS = "[,\n]";
    private final FormatValidator formatValidator;

    public IntegerParser() {
        formatValidator = new FormatValidator();
    }

    public List<Integer> parse(String stringToParse) {
        // If there is a prefix for a custom delimiter, read it then remove it
        String delimiter = DEFAULT_DELIMITERS;
        if (hasCustomDelimiter(stringToParse)) {
            delimiter = parseDelimiter(stringToParse);
            stringToParse = removeHead(stringToParse);
        }

        List<String> numberString = parseBody(stringToParse, delimiter);

        return parseNumbers(numberString);
    }

    /**
     * Finds the delimiter at the expected position from DELIMITER_REGEX.
     * Multiple custom delimiters are not supported.
     * @throws exceptions.BadDelimiterException
     */
    public String parseDelimiter(String stringToParse) {
        int delimiterIndex = DELIMITER_REGEX.indexOf(".");
        String delimiter = String.valueOf(stringToParse.charAt(delimiterIndex));
        formatValidator.checkDelimiter(delimiter);
        removeHead(stringToParse);
        return "\\" + delimiter;
    }

    /**
     * Matches any chain starting with DELIMITER_REGEX.
     */
    public boolean hasCustomDelimiter(String stringToParse) {
        String regexIfDelimiter = DELIMITER_REGEX + ".*$";
        return stringToParse.matches(regexIfDelimiter);
    }

    /**
     * @throws exceptions.BadFormatException
     */
    public List<String> parseBody(String stringToParse, String delimiter) {
        List<String> items = List.of(stringToParse.split(delimiter));
        formatValidator.checkBody(items);
        return items;
    }

    /**
     * @throws exceptions.NegativeNumberException
     */
    public List<Integer> parseNumbers(List<String> numberString) {
        List<Integer> numbers = numberString.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        formatValidator.checkNegatives(numbers);
        return numbers;
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
