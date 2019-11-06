package com.runsim.backend.nas.eap;

import com.runsim.backend.utils.octets.OctetString;

import java.util.Map;

public class AkaPrime extends ExtensibleAuthenticationProtocol {
    public EAkaSubType subType;
    public Map<EAkaAttributeType, OctetString> attributes;
}
