package tr.havelsan.ueransim.core.exceptions;

public class IncorrectImplementationException extends RuntimeException {
    private final String message;

    public IncorrectImplementationException(Class<?> type, String message) {
        this.message = "Incorrect implementation for type: " + type + ". " + message;
    }

    public IncorrectImplementationException(String message) {
        this.message = message;
    }

    public IncorrectImplementationException() {
        this.message = "";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
