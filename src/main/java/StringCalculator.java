import parsers.IntegerParser;
import parsers.Parser;
import java.util.Arrays;

public class StringCalculator {
    private final Parser<Integer> parser;

    public StringCalculator(Parser<Integer> parser) {
        this.parser = parser;
    }

    /**
     * Sums numbers listed in the String parameter.
     */
    public int Add(String numberString) {
        if (numberString.isEmpty()) {
            return 0;
        }
        Integer[] numbersArray = parser.parse(numberString);
        return Arrays.stream(numbersArray)
                .reduce(0, Integer::sum);
    }

    public static void main(String... args) {
        StringCalculator calculator = new StringCalculator(new IntegerParser());
        System.out.println(calculator.Add(args[0]));
    }
}
