package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.*;

public class PduSessionModificationCommand extends PlainSmMessage {
    public IE5gSmCause smCause;
    public IESessionAmbr sessionAmbr;
    public IEGprsTimer rqTimerValue;
    public IEAlwaysOnPduSessionIndication alwaysOnPduSessionIndication;
    public IEQoSRules authorizedQoSRules;
    public IEMappedEpsBearerContexts mappedEpsBearerContexts;
    public IEQoSFlowDescriptions authorizedQoSFlowDescriptions;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionModificationCommand() {
        super(EMessageType.PDU_SESSION_MODIFICATION_COMMAND);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x59, "smCause");
        builder.optionalIE(0x2A, "sessionAmbr");
        builder.optionalIE(0x56, "rqTimerValue");
        builder.optionalIE1(0x8, "alwaysOnPduSessionIndication");
        builder.optionalIE(0x7A, "authorizedQoSRules");
        builder.optionalIE(0x7F, "mappedEpsBearerContexts");
        builder.optionalIE(0x79, "authorizedQoSFlowDescriptions");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
