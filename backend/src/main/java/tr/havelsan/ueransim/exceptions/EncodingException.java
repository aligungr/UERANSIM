package tr.havelsan.ueransim.exceptions;

public class EncodingException extends RuntimeException {
    private final String message;

    public EncodingException(Class<?> clazz) {
        this("invalid value for " + clazz.getSimpleName());
    }

    public EncodingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
