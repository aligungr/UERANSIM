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

public class PduSessionEstablishmentAccept extends PlainSmMessage {
    public IEPduSessionType selectedPduSessionType;
    public IESscMode selectedSscMode;
    public IEQoSRules authorizedQoSRules;
    public IESessionAmbr sessionAmbr;
    public IE5gSmCause smCause;
    public IEPduAddress pduAddress;
    public IEGprsTimer rqTimerValue;
    public IESNssai sNssai;
    public IEAlwaysOnPduSessionIndication alwaysOnPduSessionIndication;
    public IEMappedEpsBearerContexts mappedEpsBearerContexts;
    public IEEapMessage eapMessage;
    public IEQoSFlowDescriptions authorizedQoSFlowDescriptions;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;
    public IEDnn dnn;

    public PduSessionEstablishmentAccept() {
        super(EMessageType.PDU_SESSION_ESTABLISHMENT_ACCEPT);
    }

    public PduSessionEstablishmentAccept(IEPduSessionType selectedPduSessionType, IESscMode selectedSscMode, IEQoSRules authorizedQoSRules, IESessionAmbr sessionAmbr, IE5gSmCause smCause, IEPduAddress pduAddress, IEGprsTimer rqTimerValue, IESNssai sNssai, IEAlwaysOnPduSessionIndication alwaysOnPduSessionIndication, IEMappedEpsBearerContexts mappedEpsBearerContexts, IEEapMessage eapMessage, IEQoSFlowDescriptions authorizedQoSFlowDescriptions, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions, IEDnn dnn) {
        this();
        this.selectedPduSessionType = selectedPduSessionType;
        this.selectedSscMode = selectedSscMode;
        this.authorizedQoSRules = authorizedQoSRules;
        this.sessionAmbr = sessionAmbr;
        this.smCause = smCause;
        this.pduAddress = pduAddress;
        this.rqTimerValue = rqTimerValue;
        this.sNssai = sNssai;
        this.alwaysOnPduSessionIndication = alwaysOnPduSessionIndication;
        this.mappedEpsBearerContexts = mappedEpsBearerContexts;
        this.eapMessage = eapMessage;
        this.authorizedQoSFlowDescriptions = authorizedQoSFlowDescriptions;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
        this.dnn = dnn;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("selectedSscMode", "selectedPduSessionType");
        builder.mandatoryIE("authorizedQoSRules");
        builder.mandatoryIE("sessionAmbr");
        builder.optionalIE(0x59, "smCause");
        builder.optionalIE(0x29, "pduAddress");
        builder.optionalIE(0x56, "rqTimerValue");
        builder.optionalIE(0x22, "sNssai");
        builder.optionalIE1(0x8, "alwaysOnPduSessionIndication");
        builder.optionalIE(0x7F, "mappedEpsBearerContexts");
        builder.optionalIE(0x78, "eapMessage");
        builder.optionalIE(0x79, "authorizedQoSFlowDescriptions");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
        builder.optionalIE(0x25, "dnn");
    }
}
