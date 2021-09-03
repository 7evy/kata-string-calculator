import java.util.Arrays;
import java.util.stream.Stream;

public class StringCalculator {

    public int Add(String numbers) throws Exception {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter;
        if (numbers.startsWith("//")) {
            delimiter = String.valueOf(numbers.charAt(2));
            numbers = numbers.split("\n", 2)[1];
        } else {
            delimiter = "[,\n]";
        }

        String[] numbersArray = numbers.split(delimiter);
        String[] negatives = Arrays.stream(numbersArray)
                .filter(s -> s.startsWith("-"))
                .toArray(String[]::new);
        if (negatives.length > 0) {
            throw new Exception("Negatives are not allowed: " + Arrays.toString(negatives));
        }

        return Arrays.stream(numbersArray)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
    }
}
