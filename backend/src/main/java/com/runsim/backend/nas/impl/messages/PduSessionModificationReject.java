package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gSmCause;
import com.runsim.backend.nas.impl.ies.IEExtendedProtocolConfigurationOptions;
import com.runsim.backend.nas.impl.ies.IEGprsTimer3;

public class PduSessionModificationReject extends PlainSmMessage {
    public IE5gSmCause smCause;
    public IEGprsTimer3 backOffTimerValue;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionModificationReject() {
        super(EMessageType.PDU_SESSION_MODIFICATION_REJECT);
    }

    public PduSessionModificationReject(IE5gSmCause smCause, IEGprsTimer3 backOffTimerValue, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.smCause = smCause;
        this.backOffTimerValue = backOffTimerValue;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("smCause");
        builder.optionalIE(0x37, "backOffTimerValue");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
