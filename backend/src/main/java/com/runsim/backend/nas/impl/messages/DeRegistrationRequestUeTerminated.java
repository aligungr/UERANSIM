package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.nas.impl.ies.IE5gMmCause;
import com.runsim.backend.nas.impl.ies.IEDeRegistrationType;
import com.runsim.backend.nas.impl.ies.IEGprsTimer2;

public class DeRegistrationRequestUeTerminated extends PlainMmMessage {
    public IEDeRegistrationType deRegistrationType;
    public IE5gMmCause mmCause;
    public IEGprsTimer2 t3346Value;

    public DeRegistrationRequestUeTerminated() {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        super.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
        super.messageType = EMessageType.DEREGISTRATION_REQUEST_UE_TERMINATED;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("deRegistrationType");
        builder.optionalIE(0x58, "mmCause");
        builder.optionalIE(0x5F, "t3346Value");
    }
}
