package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.*;

public class AuthenticationRequest extends PlainMmMessage {
    public IENasKeySetIdentifier ngKSI;
    public IEAbba abba;
    public IERand authParamRAND;
    public IEAutn authParamAUTN;
    public IEEapMessage eapMessage;

    public AuthenticationRequest() {
        super(EMessageType.AUTHENTICATION_REQUEST);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("ngKSI");
        builder.mandatoryIE("abba");
        builder.optionalIE(0x21, "authParamRAND");
        builder.optionalIE(0x20, "authParamAUTN");
        builder.optionalIE(0x78, "eapMessage");
    }
}
