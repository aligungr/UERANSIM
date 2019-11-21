package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.EPduSessionIdentity;
import com.runsim.backend.nas.impl.enums.EProcedureTransactionIdentity;
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
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.SESSION_MANAGEMENT_MESSAGES;
        super.pduSessionId = EPduSessionIdentity.NO_VAL;
        super.pti = EProcedureTransactionIdentity.NO_VAL;
        super.messageType = EMessageType.PDU_SESSION_ESTABLISHMENT_REQUEST;
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
        builder.optionalIE1(0x7B, "extendedProtocolConfigurationOptions");
    }
}
