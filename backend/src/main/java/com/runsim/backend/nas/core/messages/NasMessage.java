package com.runsim.backend.nas.core.messages;

import com.runsim.backend.nas.core.ProtocolValue;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.utils.OctetInputStream;

public abstract class NasMessage extends ProtocolValue {
    public EExtendedProtocolDiscriminator extendedProtocolDiscriminator;
    public ESecurityHeaderType securityHeaderType;

    public abstract NasMessage decodeMessage(OctetInputStream stream);
}
