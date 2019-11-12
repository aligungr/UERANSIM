package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.*;

public class AuthenticationRequest extends PlainNasMessage {
    public IENasKeySetIdentifier ngKSI;
    public IEAbba abba;
    public IEEapMessage eapMessage;
    public IERand authParamRAND;
    public IEAutn authParamAUTN;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.mandatoryIE1("ngKSI");
        builder.mandatoryIE("abba");
        builder.optionalIE(0x78, "eapMessage");
        builder.optionalIE(0x20, "authParamAUTN");
        builder.optionalIE(0x21, "authParamRAND");
    }
}
