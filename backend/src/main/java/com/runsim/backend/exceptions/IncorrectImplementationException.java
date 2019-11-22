package com.runsim.backend.exceptions;

// TODO: Bazı diğer exception'ları da buna geçir
public class IncorrectImplementationException extends RuntimeException {
    private final String message;

    public IncorrectImplementationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
