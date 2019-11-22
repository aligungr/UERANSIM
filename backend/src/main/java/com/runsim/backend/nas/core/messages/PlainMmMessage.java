package com.runsim.backend.nas.core.messages;

import com.runsim.backend.exceptions.IncorrectImplementationException;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public abstract class PlainMmMessage extends NasMessage {
    public ESecurityHeaderType securityHeaderType;
    public EMessageType messageType;

    public PlainMmMessage(EMessageType messageType) {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        this.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
        this.messageType = messageType;

        if (!messageType.isMobilityManagement())
            throw new IncorrectImplementationException("message type and super classes are inconsistent");
    }

    @Override
    public final PlainMmMessage decodeMessage(OctetInputStream stream) {
        var mess = (PlainMmMessage) decodeViaBuilder(stream);
        return mess;
    }

    public final void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        stream.writeOctet(securityHeaderType.intValue());
        stream.writeOctet(messageType.intValue());

        encodeViaBuilder(stream);
    }
}
