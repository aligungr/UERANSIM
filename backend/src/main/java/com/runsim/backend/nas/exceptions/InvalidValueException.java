package com.runsim.backend.nas.exceptions;

import com.runsim.backend.nas.core.NasValue;

public class InvalidValueException extends RuntimeException {
    private String message;

    public InvalidValueException(Class<? extends NasValue> clazz) {
        this.message = "invalid value for " + clazz.getSimpleName();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
