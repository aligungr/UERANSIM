package com.runsim.backend.nas.core.messages;

import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.EPduSessionIdentity;
import com.runsim.backend.nas.impl.enums.EProcedureTransactionIdentity;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public abstract class PlainSmMessage extends NasMessage {
    public EPduSessionIdentity pduSessionId;
    public EProcedureTransactionIdentity pti;
    public EMessageType messageType;

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
