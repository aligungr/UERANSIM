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

public class PduSessionEstablishmentRequest extends PlainSmMessage {
    public IEIntegrityProtectionMaximumDataRate integrityProtectionMaximumDataRate;
    public IEPduSessionType pduSessionType;
    public IESscMode sscMode;
    public IE5gSmCapability smCapability;
    public IEMaximumNumberOfSupportedPacketFilters maximumNumberOfSupportedPacketFilters;
    public IEAlwaysOnPduSessionRequested alwaysOnPduSessionRequested;
    public IESmPduDnRequestContainer smPduDnRequestContainer;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionEstablishmentRequest() {
        super(EMessageType.PDU_SESSION_ESTABLISHMENT_REQUEST);
    }

    public PduSessionEstablishmentRequest(IEIntegrityProtectionMaximumDataRate integrityProtectionMaximumDataRate, IEPduSessionType pduSessionType, IESscMode sscMode, IE5gSmCapability smCapability, IEMaximumNumberOfSupportedPacketFilters maximumNumberOfSupportedPacketFilters, IEAlwaysOnPduSessionRequested alwaysOnPduSessionRequested, IESmPduDnRequestContainer smPduDnRequestContainer, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.integrityProtectionMaximumDataRate = integrityProtectionMaximumDataRate;
        this.pduSessionType = pduSessionType;
        this.sscMode = sscMode;
        this.smCapability = smCapability;
        this.maximumNumberOfSupportedPacketFilters = maximumNumberOfSupportedPacketFilters;
        this.alwaysOnPduSessionRequested = alwaysOnPduSessionRequested;
        this.smPduDnRequestContainer = smPduDnRequestContainer;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("integrityProtectionMaximumDataRate");
        builder.optionalIE1(0x9, "pduSessionType");
        builder.optionalIE1(0xA, "sscMode");
        builder.optionalIE(0x28, "smCapability");
        builder.optionalIE(0x55, "maximumNumberOfSupportedPacketFilters");
        builder.optionalIE1(0xB, "alwaysOnPduSessionRequested");
        builder.optionalIE(0x39, "smPduDnRequestContainer");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
