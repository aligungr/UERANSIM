package tr.havelsan.ueransim.exceptions;

public class MtsException extends RuntimeException {
    private final String message;

    public MtsException(String format, Object... args) {
        this.message = String.format(format, args);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
