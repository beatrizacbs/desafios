package idwall.desafio.exception;

/**
 * Exception thrown when a invalid text is used
 * in the formatter method indicating that the
 * String used is not valid due to business logic in the formatter
 */
public class InvalidTextException extends Exception {

    public InvalidTextException(String message) {
        super(message);
    }
}
