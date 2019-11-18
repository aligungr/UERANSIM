package com.runsim.backend.nas.core.messages;

import com.runsim.backend.nas.core.ProtocolValue;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public abstract class NasMessage extends ProtocolValue {
    public EExtendedProtocolDiscriminator extendedProtocolDiscriminator;
    public ESecurityHeaderType securityHeaderType;

    public abstract NasMessage decodeMessage(OctetInputStream stream);

    public void encodeMessage(OctetOutputStream stream) {
        stream.writeOctet(extendedProtocolDiscriminator.intValue());
        stream.writeOctet(securityHeaderType.intValue());
    }
}
