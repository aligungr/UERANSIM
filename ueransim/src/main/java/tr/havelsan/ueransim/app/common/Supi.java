/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.utils.exceptions.NotImplementedException;

public class Supi {
    public final String type;
    public final String value;

    public Supi(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public static Supi parse(String supi) {
        if (supi == null) {
            return null;
        }
        if (supi.startsWith("imsi-")) {
            return new Supi("imsi", supi.substring("imsi-".length()));
        } else {
            // TODO: Other types not implemented yet
            throw new NotImplementedException("this supi format not implemented yet");
        }
    }

    @Override
    public String toString() {
        return type + "-" + value;
    }
}
