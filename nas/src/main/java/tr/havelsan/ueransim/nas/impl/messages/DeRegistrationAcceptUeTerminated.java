/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;

public class DeRegistrationAcceptUeTerminated extends PlainMmMessage {

    public DeRegistrationAcceptUeTerminated() {
        super(EMessageType.DEREGISTRATION_ACCEPT_UE_TERMINATED);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);
    }
}
