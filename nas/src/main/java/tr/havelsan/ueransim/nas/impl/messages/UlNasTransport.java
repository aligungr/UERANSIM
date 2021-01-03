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

public class UlNasTransport extends PlainMmMessage {
    public IEPayloadContainerType payloadContainerType;
    public IEPayloadContainer payloadContainer;
    public IEPduSessionIdentity2 pduSessionId;
    public IEPduSessionIdentity2 oldPduSessionId;
    public IERequestType requestType;
    public IESNssai sNssa;
    public IEDnn dnn;
    public IEAdditionalInformation additionalInformation;

    public UlNasTransport() {
        super(EMessageType.UL_NAS_TRANSPORT);
    }

    public UlNasTransport(IEPayloadContainerType payloadContainerType, IEPayloadContainer payloadContainer, IEPduSessionIdentity2 pduSessionId, IEPduSessionIdentity2 oldPduSessionId, IERequestType requestType, IESNssai sNssa, IEDnn dnn, IEAdditionalInformation additionalInformation) {
        this();
        this.payloadContainerType = payloadContainerType;
        this.payloadContainer = payloadContainer;
        this.pduSessionId = pduSessionId;
        this.oldPduSessionId = oldPduSessionId;
        this.requestType = requestType;
        this.sNssa = sNssa;
        this.dnn = dnn;
        this.additionalInformation = additionalInformation;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("payloadContainerType");
        builder.mandatoryIE("payloadContainer");

        builder.optionalIE(0x12, "pduSessionId");
        builder.optionalIE(0x59, "oldPduSessionId");
        builder.optionalIE1(0x8, "requestType");
        builder.optionalIE(0x22, "sNssa");
        builder.optionalIE(0x25, "dnn");
        builder.optionalIE(0x24, "additionalInformation");
    }
}
