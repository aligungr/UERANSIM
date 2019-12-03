package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IEAccessType;

public class Notification extends PlainMmMessage {
    public IEAccessType accessType;

    public Notification() {
        super(EMessageType.NOTIFICATION);
    }

    public Notification(IEAccessType accessType) {
        this();
        this.accessType = accessType;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("accessType");
    }
}
