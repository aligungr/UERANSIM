/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.exceptions;

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
