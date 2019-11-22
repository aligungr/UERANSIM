package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.*;

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
