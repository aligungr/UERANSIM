package tr.havelsan.ueransim.exceptions;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ProtocolValue;

public class ReservedOrInvalidValueException extends RuntimeException {
    private final String message;

    public ReservedOrInvalidValueException(Class<? extends ProtocolValue> clazz) {
        this("invalid or reserved value found for <" + clazz.getSimpleName() + ">");
    }

    public ReservedOrInvalidValueException(String message) {
        this.message = message;
    }

    public ReservedOrInvalidValueException(String field, int value) {
        this("invalid or reserved value found for <" + field + "> with value <" + value + ">");
    }

    public ReservedOrInvalidValueException(String field, ProtocolEnum value) {
        this("invalid or reserved value found for <" + field + "> with value <" + value.intValue() + ">");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
