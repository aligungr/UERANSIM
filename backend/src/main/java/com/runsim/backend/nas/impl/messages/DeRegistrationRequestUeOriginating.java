package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IE5gsMobileIdentity;
import com.runsim.backend.nas.impl.ies.IEDeRegistrationType;
import com.runsim.backend.nas.impl.ies.IENasKeySetIdentifier;

public class DeRegistrationRequestUeOriginating extends PlainNasMessage {
    public IEDeRegistrationType deRegistrationType;
    public IENasKeySetIdentifier ngKSI;
    public IE5gsMobileIdentity mobileIdentity;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.mandatoryIE1("ngKSI", "deRegistrationType");
        builder.mandatoryIE("mobileIdentity");
    }
}
