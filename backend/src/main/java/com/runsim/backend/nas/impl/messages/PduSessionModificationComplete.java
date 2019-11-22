package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IEExtendedProtocolConfigurationOptions;

public class PduSessionModificationComplete extends PlainSmMessage {
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionModificationComplete() {
        super(EMessageType.PDU_SESSION_MODIFICATION_COMPLETE);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
