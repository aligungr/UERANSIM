package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.*;

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
