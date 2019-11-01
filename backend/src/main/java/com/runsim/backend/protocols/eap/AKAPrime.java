package com.runsim.backend.protocols.eap;

import com.runsim.backend.protocols.octets.OctetString;

import java.util.Map;

public class AKAPrime extends ExtensibleAuthenticationProtocol {
    public AKASubType subType;
    public Map<AKAAttributeType, OctetString> attributes;
}
