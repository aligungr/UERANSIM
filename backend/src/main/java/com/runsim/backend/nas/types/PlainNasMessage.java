package com.runsim.backend.nas.types;

public class PlainNasMessage extends NasMessage {
    private ExtendedProtocolDiscriminator extendedProtocolDiscriminator;
    private SecurityHeaderType securityHeaderType;
    private MessageType messageType;

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

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
