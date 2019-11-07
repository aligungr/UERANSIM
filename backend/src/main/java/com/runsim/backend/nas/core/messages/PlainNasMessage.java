package com.runsim.backend.nas.core.messages;

import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.utils.OctetOutputStream;

public abstract class PlainNasMessage extends NasMessage {
    public EMessageType messageType;

    @Override
    public void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        stream.writeOctet(messageType.value);
    }
}
