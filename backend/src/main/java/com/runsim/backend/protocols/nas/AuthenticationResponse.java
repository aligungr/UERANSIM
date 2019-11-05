package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.eap.ExtensibleAuthenticationProtocol;

public class AuthenticationResponse extends PlainNASMessage {
    // Optional
    public AuthenticationResponseParameter authenticationResponseParameter;

    // Optional
    public ExtensibleAuthenticationProtocol extensibleAuthenticationProtocol;
}
