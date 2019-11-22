package com.runsim.backend.nas.core.messages;

import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.EPduSessionIdentity;
import com.runsim.backend.nas.impl.enums.EProcedureTransactionIdentity;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public abstract class PlainSmMessage extends NasMessage {
    public EPduSessionIdentity pduSessionId;
    public EProcedureTransactionIdentity pti;
    public EMessageType messageType;

    public PlainSmMessage(EMessageType messageType) {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.SESSION_MANAGEMENT_MESSAGES;
        this.pduSessionId = EPduSessionIdentity.NO_VAL;
        this.pti = EProcedureTransactionIdentity.NO_VAL;
        this.messageType = messageType;
    }

    @Override
    public final PlainSmMessage decodeMessage(OctetInputStream stream) {
        var mess = (PlainSmMessage) decodeViaBuilder(stream);
        return mess;
    }

    public final void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        stream.writeOctet(pduSessionId.intValue());
        stream.writeOctet(pti.intValue());
        stream.writeOctet(messageType.intValue());

        encodeViaBuilder(stream);
    }
}
