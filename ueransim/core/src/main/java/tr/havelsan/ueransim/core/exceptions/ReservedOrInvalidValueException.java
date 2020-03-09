package tr.havelsan.ueransim.core.exceptions;

public class ReservedOrInvalidValueException extends RuntimeException {
    private final String message;

    public ReservedOrInvalidValueException(Class<?> clazz) {
        this("invalid or reserved value found for <" + clazz.getSimpleName() + ">");
    }

    public ReservedOrInvalidValueException(String message) {
        this.message = message;
    }

    public ReservedOrInvalidValueException(String field, Object value) {
        this("invalid or reserved value found for <" + field + "> with value <" + value + ">");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
