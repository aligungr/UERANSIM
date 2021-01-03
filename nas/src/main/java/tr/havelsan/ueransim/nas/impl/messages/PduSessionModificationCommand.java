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

    public PduSessionModificationCommand(IE5gSmCause smCause, IESessionAmbr sessionAmbr, IEGprsTimer rqTimerValue, IEAlwaysOnPduSessionIndication alwaysOnPduSessionIndication, IEQoSRules authorizedQoSRules, IEMappedEpsBearerContexts mappedEpsBearerContexts, IEQoSFlowDescriptions authorizedQoSFlowDescriptions, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.smCause = smCause;
        this.sessionAmbr = sessionAmbr;
        this.rqTimerValue = rqTimerValue;
        this.alwaysOnPduSessionIndication = alwaysOnPduSessionIndication;
        this.authorizedQoSRules = authorizedQoSRules;
        this.mappedEpsBearerContexts = mappedEpsBearerContexts;
        this.authorizedQoSFlowDescriptions = authorizedQoSFlowDescriptions;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
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
