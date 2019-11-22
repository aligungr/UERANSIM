package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gsMobileIdentity;
import com.runsim.backend.nas.impl.ies.IEDeRegistrationType;
import com.runsim.backend.nas.impl.ies.IENasKeySetIdentifier;

public class DeRegistrationRequestUeOriginating extends PlainMmMessage {
    public IEDeRegistrationType deRegistrationType;
    public IENasKeySetIdentifier ngKSI;
    public IE5gsMobileIdentity mobileIdentity;

    public DeRegistrationRequestUeOriginating() {
        super(EMessageType.DEREGISTRATION_REQUEST_UE_ORIGINATING);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("ngKSI", "deRegistrationType");
        builder.mandatoryIE("mobileIdentity");
    }
}
