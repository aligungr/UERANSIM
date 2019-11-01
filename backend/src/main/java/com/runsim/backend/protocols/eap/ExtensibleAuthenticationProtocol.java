package com.runsim.backend.protocols.eap;

import com.runsim.backend.protocols.core.ProtocolValue;
import com.runsim.backend.protocols.octets.Octet;
import com.runsim.backend.protocols.octets.Octet2;

public class ExtensibleAuthenticationProtocol extends ProtocolValue {
    public Code code;
    public Octet id;
    public Octet2 length;
    public EAPType EAPType;
}
