import parsers.IntegerParser;
import parsers.Parser;

import java.util.Arrays;
import java.util.List;

public class StringCalculator {
    private final Parser<Integer> parser;

    public StringCalculator(Parser<Integer> parser) {
        this.parser = parser;
    }

    /**
     * Sums numbers listed in the String parameter.
     */
    public int add(String numberString) {
        if (numberString.isEmpty()) {
            return 0;
        }
        List<Integer> numbers = parser.parse(numberString);
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

    public static void main(String... args) {
        StringCalculator calculator = new StringCalculator(new IntegerParser());
        List<String> arguments = Arrays.asList("", "1", "1,2\n3,4\n5", "//;\n1;2;3;");
        for (String s:arguments) {
            System.out.println(calculator.add(s));
        }
    }
}
