package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedHashMap;

public final class EapAkaAttributes {
    private LinkedHashMap<EEapAkaAttributeType, OctetString> attributes;

    public EapAkaAttributes() {
        this.attributes = new LinkedHashMap<>();
    }

    public LinkedHashMap<EEapAkaAttributeType, OctetString> getAttributes() {
        return attributes;
    }
}
