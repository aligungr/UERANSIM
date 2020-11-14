/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsIdentityType;

public class IdentityRequest extends PlainMmMessage {
    public IE5gsIdentityType identityType;

    public IdentityRequest() {
        super(EMessageType.IDENTITY_REQUEST);
    }

    public IdentityRequest(IE5gsIdentityType identityType) {
        this();
        this.identityType = identityType;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("identityType");
    }
}
