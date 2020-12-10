/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.exceptions;

public class SimException extends RuntimeException {

    public SimException() {
    }

    public SimException(String message) {
        super(message);
    }

    public SimException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimException(Throwable cause) {
        super(cause);
    }

    public SimException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
