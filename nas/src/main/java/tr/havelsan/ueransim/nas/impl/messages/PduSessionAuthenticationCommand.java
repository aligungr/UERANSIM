/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.ies.IEExtendedProtocolConfigurationOptions;

public class PduSessionAuthenticationCommand extends PlainSmMessage {
    public IEEapMessage eapMessage;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionAuthenticationCommand() {
        super(EMessageType.PDU_SESSION_AUTHENTICATION_COMMAND);
    }

    public PduSessionAuthenticationCommand(IEEapMessage eapMessage, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.eapMessage = eapMessage;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("eapMessage");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
