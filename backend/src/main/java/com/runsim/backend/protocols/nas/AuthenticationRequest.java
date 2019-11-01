package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.eap.ExtensibleAuthenticationProtocol;

public class AuthenticationRequest extends PlainNASMessage {
    public NASKeySetIdentifier ngKSI;
    public ABBA abba;
    public ExtensibleAuthenticationProtocol eap;
}
