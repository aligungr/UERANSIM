/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IE5gSmCause;
import tr.havelsan.ueransim.nas.impl.ies.IEExtendedProtocolConfigurationOptions;

public class PduSessionModificationCommandReject extends PlainSmMessage {
    public IE5gSmCause smCause;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionModificationCommandReject() {
        super(EMessageType.PDU_SESSION_MODIFICATION_COMMAND_REJECT);
    }

    public PduSessionModificationCommandReject(IE5gSmCause smCause, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.smCause = smCause;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("smCause");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
