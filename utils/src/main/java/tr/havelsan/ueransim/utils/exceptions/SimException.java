/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
