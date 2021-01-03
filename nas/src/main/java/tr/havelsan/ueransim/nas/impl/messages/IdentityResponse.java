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
