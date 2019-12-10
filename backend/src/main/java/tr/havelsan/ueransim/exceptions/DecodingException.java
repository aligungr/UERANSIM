package tr.havelsan.ueransim.exceptions;

import tr.havelsan.ueransim.nas.core.ProtocolValue;

public class DecodingException extends RuntimeException {
    private final String message;

    public DecodingException(Class<? extends ProtocolValue> clazz) {
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
