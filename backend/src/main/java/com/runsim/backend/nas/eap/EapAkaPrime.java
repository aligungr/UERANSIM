package com.runsim.backend.nas.eap;

import com.runsim.backend.utils.octets.OctetString;

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
