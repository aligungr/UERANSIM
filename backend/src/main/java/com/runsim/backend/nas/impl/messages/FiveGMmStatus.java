package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gMmCause;

public class FiveGMmStatus extends PlainMmMessage {
    public IE5gMmCause mmCause;

    public FiveGMmStatus() {
        super(EMessageType.FIVEG_MM_STATUS);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("mmCause");
    }
}
