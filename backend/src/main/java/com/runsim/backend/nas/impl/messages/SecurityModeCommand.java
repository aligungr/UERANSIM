package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.nas.impl.ies.*;

public class SecurityModeCommand extends PlainMmMessage {
    public IENasSecurityAlgorithms selectedNasSecurityAlgorithms;
    public IENasKeySetIdentifier ngKsi;
    public IEUeSecurityCapability replayedUeSecurityCapabilities;
    public IEImeiSvRequest imeiSvRequest;
    public IEEpsNasSecurityAlgorithms epsNasSecurityAlgorithms;
    public IEAdditional5gSecurityInformation additional5gSecurityInformation;
    public IEEapMessage eapMessage;
    public IEAbba abba;
    public IES1UeNetworkCapability replayedS1UeNetworkCapability;

    public SecurityModeCommand() {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        super.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
        super.messageType = EMessageType.SECURITY_MODE_COMMAND;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("selectedNasSecurityAlgorithms");
        builder.mandatoryIE1("ngKsi");
        builder.mandatoryIE("replayedUeSecurityCapabilities");
        builder.optionalIE1(0xE, "imeiSvRequest");
        builder.optionalIE(0x57, "epsNasSecurityAlgorithms");
        builder.optionalIE(0x36, "additional5gSecurityInformation");
        builder.optionalIE(0x78, "eapMessage");
        builder.optionalIE(0x38, "abba");
        builder.optionalIE(0x19, "replayedS1UeNetworkCapability");
    }
}
