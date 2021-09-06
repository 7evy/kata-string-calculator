package exceptions;

import java.util.ArrayList;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(ArrayList<Integer> numbers) {
        super("Negative numbers are not allowed: " + numbers.toString());
    }
}
