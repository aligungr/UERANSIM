/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsMobileIdentity;

public class IdentityResponse extends PlainMmMessage {
    public IE5gsMobileIdentity mobileIdentity;

    public IdentityResponse() {
        super(EMessageType.IDENTITY_RESPONSE);
    }

    public IdentityResponse(IE5gsMobileIdentity mobileIdentity) {
        this();
        this.mobileIdentity = mobileIdentity;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("mobileIdentity");
    }
}
