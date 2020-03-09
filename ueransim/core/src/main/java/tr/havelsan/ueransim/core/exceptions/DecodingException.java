package tr.havelsan.ueransim.core.exceptions;

public class DecodingException extends RuntimeException {
    private final String message;

    public DecodingException(Class<?> clazz) {
        this("invalid value for " + clazz.getSimpleName());
    }

    public DecodingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
