/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IEPduSessionStatus;

public class NotificationResponse extends PlainMmMessage {
    public IEPduSessionStatus pduSessionStatus;

    public NotificationResponse() {
        super(EMessageType.NOTIFICATION_RESPONSE);
    }

    public NotificationResponse(IEPduSessionStatus pduSessionStatus) {
        this();
        this.pduSessionStatus = pduSessionStatus;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x50, "pduSessionStatus");
    }
}
