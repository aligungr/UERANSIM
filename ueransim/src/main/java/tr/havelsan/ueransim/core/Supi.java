package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.core.exceptions.NotImplementedException;

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
}
