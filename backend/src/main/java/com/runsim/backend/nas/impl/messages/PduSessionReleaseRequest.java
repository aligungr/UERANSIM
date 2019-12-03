package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gSmCause;
import com.runsim.backend.nas.impl.ies.IEExtendedProtocolConfigurationOptions;

public class PduSessionReleaseRequest extends PlainSmMessage {
    public IE5gSmCause smCause;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionReleaseRequest() {
        super(EMessageType.PDU_SESSION_RELEASE_REQUEST);
    }

    public PduSessionReleaseRequest(IE5gSmCause smCause, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.smCause = smCause;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x59, "smCause");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
