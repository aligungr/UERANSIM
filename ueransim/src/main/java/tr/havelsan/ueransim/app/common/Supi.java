/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.utils.exceptions.NotImplementedException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supi supi = (Supi) o;
        return Objects.equals(type, supi.type) &&
                Objects.equals(value, supi.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
