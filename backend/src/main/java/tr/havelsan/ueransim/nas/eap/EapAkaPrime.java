package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedHashMap;

public class EapAkaPrime extends Eap {
    public EEapAkaSubType subType;
    public LinkedHashMap<EEapAkaAttributeType, OctetString> attributes;

    public EapAkaPrime() {
    }

    public EapAkaPrime(EEapAkaSubType subType, LinkedHashMap<EEapAkaAttributeType, OctetString> attributeMap) {
        this.subType = subType;
        this.attributes = attributeMap;
    }

    public EapAkaPrime(EEapAkaSubType subType, EapAkaAttributes attributes) {
        this.subType = subType;
        this.attributes = attributes.getAttributes();
    }
}
