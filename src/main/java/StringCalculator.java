import java.util.Arrays;

public class StringCalculator {

    public int Add(String numberString) throws Exception {
        if (numberString.isEmpty()) {
            return 0;
        }

        // Check delimiter:
        // - format is //<delimiter>\n at the beginning,
        // - only single character delimiters
        // - numbers are not allowed
        String delimiter;
        if (numberString.matches("//[^[0-9]]\n.*$")) {
            // Custom delimiter is after // prefix
            delimiter = String.valueOf(numberString.charAt(2));
            numberString = numberString.split("\n")[1];
        } else {
            // Default delimiters: , and \n
            delimiter = "[,\n]";
        }

        // Check String format:
        // - numbers should be separated by a single delimiter
        // - starting delimiter is forbidden but trailing is allowed
        // - negative numbers are considered valid syntax
        String format = "(-?[0-9]+" + delimiter + ")*(-?[0-9]+)?";
        if (!numberString.matches(format)) {
            throw new Exception("Bad format");
        }

        // Parse the numbers from the String using the delimiter
        Integer[] numbersArray = Arrays.stream(numberString.split(delimiter))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        // Check for forbidden negative numbers
        Integer[] negatives = findNegatives(numbersArray);
        if (negatives.length > 0) {
            System.out.println(Arrays.toString(negatives));
            throw new Exception("Negatives are not allowed: " + Arrays.toString(negatives));
        }

        return Arrays.stream(numbersArray)
                .reduce(0, Integer::sum);
    }

    public Integer[] findNegatives(Integer[] numbersArray) {
        return Arrays.stream(numbersArray)
                .filter(n -> n < 0)
                .toArray(Integer[]::new);
    }
}
