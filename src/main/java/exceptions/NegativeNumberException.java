package exceptions;

import java.util.List;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(List<Integer> numbers) {
        super("Negative numbers are not allowed: " + numbers.toString());
    }
}
