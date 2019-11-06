package com.runsim.backend.protocols.nas.messages;

import com.runsim.backend.protocols.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.protocols.nas.impl.enums.EMessageType;
import com.runsim.backend.protocols.nas.impl.enums.ESecurityHeaderType;

public abstract class PlainNasMessage extends NasMessage {
    public EExtendedProtocolDiscriminator extendedProtocolDiscriminator;
    public ESecurityHeaderType securityHeaderType;
    public EMessageType messageType;
}
