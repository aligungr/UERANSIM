package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gSmCause;
import com.runsim.backend.nas.impl.ies.IEExtendedProtocolConfigurationOptions;

public class PduSessionModificationCommandReject extends PlainSmMessage {
    public IE5gSmCause smCause;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionModificationCommandReject() {
        super(EMessageType.PDU_SESSION_MODIFICATION_COMMAND_REJECT);
    }

    public PduSessionModificationCommandReject(IE5gSmCause smCause, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.smCause = smCause;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("smCause");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
