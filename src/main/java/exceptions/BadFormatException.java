package exceptions;

public class BadFormatException extends RuntimeException {
    public BadFormatException(String message) {
        super("Bad format: " + message);
    }
}
