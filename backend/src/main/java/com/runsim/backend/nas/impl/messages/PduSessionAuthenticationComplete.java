package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IEEapMessage;
import com.runsim.backend.nas.impl.ies.IEExtendedProtocolConfigurationOptions;

public class PduSessionAuthenticationComplete extends PlainSmMessage {
    public IEEapMessage eapMessage;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionAuthenticationComplete() {
        super(EMessageType.PDU_SESSION_AUTHENTICATION_COMPLETE);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("eapMessage");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
