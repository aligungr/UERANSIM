package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.EPduSessionIdentity;
import com.runsim.backend.nas.impl.enums.EProcedureTransactionIdentity;
import com.runsim.backend.nas.impl.ies.*;

public class PduSessionEstablismentAccept extends PlainSmMessage {
    public IEPduSessionType selectedPduSessionType;
    public IESscMode selectedSscMode;
    public IEQoSRules authorizedQoSRules;
    public IESessionAmbr sessionAmbr;
    public IE5gSmCause smCause;
    public IEPduAddress pduAddress;
    public IEGprsTimer rqTimerValue;
    public IESNssai snssai;
    public IEAlwaysOnPduSessionIndication alwaysOnPduSessionIndication;
    public IEMappedEpsBearerContexts mappedEpsBearerContexts;
    public IEEapMessage eapMessage;
    public IEQoSFlowDescriptions authorizedQoSFlowDescriptions;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;
    public IEDnn dnn;


    public PduSessionEstablismentAccept() {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.SESSION_MANAGEMENT_MESSAGES;
        super.pduSessionId = EPduSessionIdentity.NO_VAL;
        super.pti = EProcedureTransactionIdentity.NO_VAL;
        super.messageType = EMessageType.PDU_SESSION_ESTABLISHMENT_ACCEPT;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("selectedSscMode", "selectedPduSessionType");
        builder.mandatoryIE("authorizedQoSRules");
        builder.mandatoryIE("sessionAmbr");
        builder.mandatoryIE("authorizedQoSRules");
        builder.mandatoryIE("sessionAmbr");
        builder.optionalIE(0x59, "smCause");
        builder.optionalIE(0x29, "pduAddress");
        builder.optionalIE(0x56, "rqTimerValue");
        builder.optionalIE(0x22, "snssai");
        builder.optionalIE1(0x8, "alwaysOnPduSessionIndication");
        builder.optionalIE(0x7F, "mappedEpsBearerContexts");
        builder.optionalIE(0x78, "eapMessage");
        builder.optionalIE(0x79, "authorizedQoSFlowDescriptions");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
        builder.optionalIE(0x25, "dnn");
    }
}
