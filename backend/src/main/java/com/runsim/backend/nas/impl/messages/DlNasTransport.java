package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.*;

public class DlNasTransport extends PlainNasMessage {
    public IEPayloadContainerType payloadContainerType;
    public IEPayloadContainer payloadContainer;
    public IEPduSessionIdentity2 pduSessionId;
    public IEAdditionalInformation additionalInformation;
    public IE5gMmCause mmCause;
    public IEGprsTimer3 backOffTimerValue;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.mandatoryIE1("payloadContainerType");
        builder.mandatoryIE("payloadContainer");

        builder.optionalIE(0x12, "pduSessionId");
        builder.optionalIE(0x24, "additionalInformation");
        builder.optionalIE(0x58, "mmCause");
        builder.optionalIE(0x37, "backOffTimerValue");
    }
}
