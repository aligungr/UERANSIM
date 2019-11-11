package com.runsim.backend.nas.eap;

import com.runsim.backend.utils.octets.OctetString;

import java.util.LinkedHashMap;

public class AkaPrime extends EAP {
    public EAkaSubType subType;
    public LinkedHashMap<EAkaAttributeType, OctetString> attributes;
}
