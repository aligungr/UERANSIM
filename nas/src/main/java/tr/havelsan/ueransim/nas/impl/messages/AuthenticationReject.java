/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;

public class AuthenticationReject extends PlainMmMessage {
    public IEEapMessage eapMessage;

    public AuthenticationReject() {
        super(EMessageType.AUTHENTICATION_REJECT);
    }

    public AuthenticationReject(IEEapMessage eapMessage) {
        this();
        this.eapMessage = eapMessage;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x78, "eapMessage");
    }
}
