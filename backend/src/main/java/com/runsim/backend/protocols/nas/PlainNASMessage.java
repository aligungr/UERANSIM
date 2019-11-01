package com.runsim.backend.protocols.nas;

public class PlainNASMessage extends NASMessage {
    public ExtendedProtocolDiscriminator extendedProtocolDiscriminator;
    public SecurityHeaderType securityHeaderType;
    public MessageType messageType;
}
