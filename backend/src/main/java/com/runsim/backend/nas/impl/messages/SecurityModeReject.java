package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gMmCause;

public class SecurityModeReject extends PlainMmMessage {
    public IE5gMmCause mmCause;

    public SecurityModeReject() {
        super(EMessageType.SECURITY_MODE_REJECT);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("mmCause");
    }
}
