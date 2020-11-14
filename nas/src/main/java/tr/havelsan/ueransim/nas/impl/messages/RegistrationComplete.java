/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IESorTransparentContainer;

public class RegistrationComplete extends PlainMmMessage {
    public IESorTransparentContainer sorTransparentContainer;

    public RegistrationComplete() {
        super(EMessageType.REGISTRATION_COMPLETE);
    }

    public RegistrationComplete(IESorTransparentContainer sorTransparentContainer) {
        this();
        this.sorTransparentContainer = sorTransparentContainer;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x73, "sorTransparentContainer");
    }
}
