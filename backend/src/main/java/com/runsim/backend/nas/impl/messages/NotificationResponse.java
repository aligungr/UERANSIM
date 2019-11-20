package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.nas.impl.ies.IEPduSessionStatus;

public class NotificationResponse extends PlainMmMessage {
    public IEPduSessionStatus pduSessionStatus;

    public NotificationResponse() {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        super.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
        super.messageType = EMessageType.NOTIFICATION_RESPONSE;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x50, "pduSessionStatus");
    }
}
