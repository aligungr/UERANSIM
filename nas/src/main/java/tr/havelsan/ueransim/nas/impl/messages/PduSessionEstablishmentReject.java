/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.*;

public class PduSessionEstablishmentReject extends PlainSmMessage {
    public IE5gSmCause smCause;
    public IEGprsTimer3 backOffTimerValue;
    public IEAllowedSscMode allowedSscMode;
    public IEEapMessage eapMessage;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionEstablishmentReject() {
        super(EMessageType.PDU_SESSION_ESTABLISHMENT_REJECT);
    }

    public PduSessionEstablishmentReject(IE5gSmCause smCause, IEGprsTimer3 backOffTimerValue, IEAllowedSscMode allowedSscMode, IEEapMessage eapMessage, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.smCause = smCause;
        this.backOffTimerValue = backOffTimerValue;
        this.allowedSscMode = allowedSscMode;
        this.eapMessage = eapMessage;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("smCause");
        builder.optionalIE(0x37, "backOffTimerValue");
        builder.optionalIE1(0xF, "allowedSscMode");
        builder.optionalIE(0x78, "eapMessage");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
