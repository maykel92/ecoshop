package exceptions;

public class TypeHeaderException extends RuntimeException {

    public TypeHeaderException(String message) {
        super(message);
    }

    public TypeHeaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
