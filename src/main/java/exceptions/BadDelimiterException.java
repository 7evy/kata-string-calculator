package exceptions;

public class BadDelimiterException extends RuntimeException {
    public BadDelimiterException(String delimiter) {
        super("Bad delimiter: " + delimiter);
    }
}
