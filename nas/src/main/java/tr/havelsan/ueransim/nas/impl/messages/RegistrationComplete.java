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
