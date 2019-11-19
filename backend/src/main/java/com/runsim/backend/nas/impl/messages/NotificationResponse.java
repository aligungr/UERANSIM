package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IEPduSessionStatus;

public class NotificationResponse extends PlainNasMessage {
    public IEPduSessionStatus pduSessionStatus;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.optionalIE(0x50, "pduSessionStatus");
    }
}
