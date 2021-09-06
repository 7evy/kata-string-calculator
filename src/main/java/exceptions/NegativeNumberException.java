package exceptions;

import java.util.Arrays;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(Integer... numbers) {
        super("Negative numbers are not allowed: " + Arrays.toString(numbers));
    }
}
