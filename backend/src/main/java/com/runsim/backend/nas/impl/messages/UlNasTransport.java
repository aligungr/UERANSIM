package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.*;

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
