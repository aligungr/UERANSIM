/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.*;

public class AuthenticationRequest extends PlainMmMessage {
    public IENasKeySetIdentifier ngKSI;
    public IEAbba abba;
    public IEAuthenticationParameterRand authParamRAND;
    public IEAuthenticationParameterAutn authParamAUTN;
    public IEEapMessage eapMessage;

    public AuthenticationRequest() {
        super(EMessageType.AUTHENTICATION_REQUEST);
    }

    public AuthenticationRequest(EMessageType messageType, IENasKeySetIdentifier ngKSI, IEAbba abba, IEAuthenticationParameterRand authParamRAND, IEAuthenticationParameterAutn authParamAUTN, IEEapMessage eapMessage) {
        this();
        this.ngKSI = ngKSI;
        this.abba = abba;
        this.authParamRAND = authParamRAND;
        this.authParamAUTN = authParamAUTN;
        this.eapMessage = eapMessage;
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
