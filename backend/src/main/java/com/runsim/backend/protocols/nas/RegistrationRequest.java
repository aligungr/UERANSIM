package com.runsim.backend.protocols.nas;

public class RegistrationRequest extends PlainNASMessage {
    public FiveGSRegistrationType registrationType;
    public NASKeySetIdentifier nasKeySetIdentifier;
    public FiveGSMobileIdentity mobileIdentity;

    /* Optional fields */
    public UESecurityCapability ueSecurityCapability;
}
