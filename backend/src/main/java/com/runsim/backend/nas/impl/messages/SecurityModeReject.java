package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IE5gMmCause;

public class SecurityModeReject extends PlainNasMessage {
    public IE5gMmCause mmCause;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.mandatoryIE("mmCause");
    }
}
