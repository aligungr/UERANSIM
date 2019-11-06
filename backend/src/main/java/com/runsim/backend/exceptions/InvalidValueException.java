package com.runsim.backend.exceptions;

import com.runsim.backend.nas.core.ProtocolValue;

public class InvalidValueException extends RuntimeException {
    private final String message;

    public InvalidValueException(Class<? extends ProtocolValue> clazz) {
        this("invalid value for " + clazz.getSimpleName());
    }

    public InvalidValueException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
