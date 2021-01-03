/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
