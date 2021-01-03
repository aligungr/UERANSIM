/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
