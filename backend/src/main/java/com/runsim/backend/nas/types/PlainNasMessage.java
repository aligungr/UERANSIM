package com.runsim.backend.nas.types;

public class PlainNasMessage extends NasMessage {
    private ExtendedProtocolDiscriminator extendedProtocolDiscriminator;
    private SecurityHeaderType securityHeaderType;

    public ExtendedProtocolDiscriminator getExtendedProtocolDiscriminator() {
        return extendedProtocolDiscriminator;
    }

    public void setExtendedProtocolDiscriminator(ExtendedProtocolDiscriminator extendedProtocolDiscriminator) {
        this.extendedProtocolDiscriminator = extendedProtocolDiscriminator;
    }

    public SecurityHeaderType getSecurityHeaderType() {
        return securityHeaderType;
    }

    public void setSecurityHeaderType(SecurityHeaderType securityHeaderType) {
        this.securityHeaderType = securityHeaderType;
    }
}
