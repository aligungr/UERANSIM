package com.runsim.backend.nas.eap;

import com.runsim.backend.utils.octets.OctetString;

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
