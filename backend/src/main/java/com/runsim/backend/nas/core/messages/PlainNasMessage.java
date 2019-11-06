package com.runsim.backend.nas.core.messages;

import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;

public abstract class PlainNasMessage extends NasMessage {
    public EExtendedProtocolDiscriminator extendedProtocolDiscriminator;
    public ESecurityHeaderType securityHeaderType;
    public EMessageType messageType;
}
