package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gsMobileIdentity;
import com.runsim.backend.nas.impl.ies.IENasMessageContainer;

public class SecurityModeComplete extends PlainMmMessage {
    public IE5gsMobileIdentity imeiSv;
    public IENasMessageContainer nasMessageContainer;

    public SecurityModeComplete() {
        super(EMessageType.SECURITY_MODE_COMPLETE);
    }

    public SecurityModeComplete(IE5gsMobileIdentity imeiSv, IENasMessageContainer nasMessageContainer) {
        this();
        this.imeiSv = imeiSv;
        this.nasMessageContainer = nasMessageContainer;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x77, "imeiSv");
        builder.optionalIE(0x71, "nasMessageContainer");
    }
}
