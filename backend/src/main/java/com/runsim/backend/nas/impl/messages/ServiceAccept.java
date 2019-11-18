package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.*;

public class ServiceAccept extends PlainNasMessage {
    public IEPduSessionStatus pduSessionStatus;
    public IEPduSessionReactivationResult pduSessionReactivationResult;
    public IEPduSessionReactivationResultErrorCause pduSessionReactivationResultErrorCause;
    public IEEapMessage eapMessage;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.optionalIE(0x50, "pduSessionStatus");
        builder.optionalIE(0x26, "pduSessionReactivationResult");
        builder.optionalIE(0x72, "pduSessionReactivationResultErrorCause");
        builder.optionalIE(0x78, "eapMessage");
    }
}
