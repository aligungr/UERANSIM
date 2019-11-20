package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.ies.IEEapMessage;
import com.runsim.backend.nas.impl.ies.IEPduSessionReactivationResult;
import com.runsim.backend.nas.impl.ies.IEPduSessionReactivationResultErrorCause;
import com.runsim.backend.nas.impl.ies.IEPduSessionStatus;

public class ServiceAccept extends PlainMmMessage {
    public IEPduSessionStatus pduSessionStatus;
    public IEPduSessionReactivationResult pduSessionReactivationResult;
    public IEPduSessionReactivationResultErrorCause pduSessionReactivationResultErrorCause;
    public IEEapMessage eapMessage;

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x50, "pduSessionStatus");
        builder.optionalIE(0x26, "pduSessionReactivationResult");
        builder.optionalIE(0x72, "pduSessionReactivationResultErrorCause");
        builder.optionalIE(0x78, "eapMessage");
    }
}
