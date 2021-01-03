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

public class PduSessionModificationRequest extends PlainSmMessage {
    public IE5gSmCapability smCapability;
    public IE5gSmCause smCause;
    public IEMaximumNumberOfSupportedPacketFilters maximumNumberOfSupportedPacketFilters;
    public IEAlwaysOnPduSessionRequested alwaysOnPduSessionRequested;
    public IEIntegrityProtectionMaximumDataRate integrityProtectionMaximumDataRate;
    public IEQoSRules requestedQosRules;
    public IEQoSFlowDescriptions requestedQosFlowDescriptions;
    public IEMappedEpsBearerContexts mappedEpsBearerContexts;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionModificationRequest() {
        super(EMessageType.PDU_SESSION_MODIFICATION_REQUEST);
    }

    public PduSessionModificationRequest(IE5gSmCapability smCapability, IE5gSmCause smCause, IEMaximumNumberOfSupportedPacketFilters maximumNumberOfSupportedPacketFilters, IEAlwaysOnPduSessionRequested alwaysOnPduSessionRequested, IEIntegrityProtectionMaximumDataRate integrityProtectionMaximumDataRate, IEQoSRules requestedQosRules, IEQoSFlowDescriptions requestedQosFlowDescriptions, IEMappedEpsBearerContexts mappedEpsBearerContexts, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.smCapability = smCapability;
        this.smCause = smCause;
        this.maximumNumberOfSupportedPacketFilters = maximumNumberOfSupportedPacketFilters;
        this.alwaysOnPduSessionRequested = alwaysOnPduSessionRequested;
        this.integrityProtectionMaximumDataRate = integrityProtectionMaximumDataRate;
        this.requestedQosRules = requestedQosRules;
        this.requestedQosFlowDescriptions = requestedQosFlowDescriptions;
        this.mappedEpsBearerContexts = mappedEpsBearerContexts;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x28, "smCapability");
        builder.optionalIE(0x59, "smCause");
        builder.optionalIE(0x55, "maximumNumberOfSupportedPacketFilters");
        builder.optionalIE1(0xB, "alwaysOnPduSessionRequested");
        builder.optionalIE(0x13, "integrityProtectionMaximumDataRate");
        builder.optionalIE(0x7A, "requestedQosRules");
        builder.optionalIE(0x79, "requestedQosFlowDescriptions");
        builder.optionalIE(0x7F, "mappedEpsBearerContexts");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
