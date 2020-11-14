/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IEExtendedProtocolConfigurationOptions;

public class PduSessionModificationComplete extends PlainSmMessage {
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionModificationComplete() {
        super(EMessageType.PDU_SESSION_MODIFICATION_COMPLETE);
    }

    public PduSessionModificationComplete(IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
