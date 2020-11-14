/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.exceptions;

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
