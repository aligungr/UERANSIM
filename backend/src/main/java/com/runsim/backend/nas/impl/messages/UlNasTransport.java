package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.*;

public class UlNasTransport extends PlainNasMessage {
    public IEPayloadContainerType payloadContainerType;
    public IEPayloadContainer payloadContainer;
    public IEPduSessionIdentity2 pduSessionId;
    public IEPduSessionIdentity2 oldPduSessionId;
    public IERequestType requestType;
    public IESNssa sNssa;
    public IEDnn dnn;
    public IEAdditionalInformation additionalInformation;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

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
