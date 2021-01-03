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
import tr.havelsan.ueransim.nas.impl.ies.*;

public class DlNasTransport extends PlainMmMessage {
    public IEPayloadContainerType payloadContainerType;
    public IEPayloadContainer payloadContainer;
    public IEPduSessionIdentity2 pduSessionId;
    public IEAdditionalInformation additionalInformation;
    public IE5gMmCause mmCause;
    public IEGprsTimer3 backOffTimerValue;

    public DlNasTransport() {
        super(EMessageType.DL_NAS_TRANSPORT);
    }

    public DlNasTransport(IEPayloadContainerType payloadContainerType, IEPayloadContainer payloadContainer, IEPduSessionIdentity2 pduSessionId, IEAdditionalInformation additionalInformation, IE5gMmCause mmCause, IEGprsTimer3 backOffTimerValue) {
        this();
        this.payloadContainerType = payloadContainerType;
        this.payloadContainer = payloadContainer;
        this.pduSessionId = pduSessionId;
        this.additionalInformation = additionalInformation;
        this.mmCause = mmCause;
        this.backOffTimerValue = backOffTimerValue;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("payloadContainerType");
        builder.mandatoryIE("payloadContainer");

        builder.optionalIE(0x12, "pduSessionId");
        builder.optionalIE(0x24, "additionalInformation");
        builder.optionalIE(0x58, "mmCause");
        builder.optionalIE(0x37, "backOffTimerValue");
    }
}
