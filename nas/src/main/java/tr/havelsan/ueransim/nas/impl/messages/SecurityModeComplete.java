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
import tr.havelsan.ueransim.nas.impl.ies.IE5gsMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IENasMessageContainer;

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
