/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEDeRegistrationType;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;

public class DeRegistrationRequestUeOriginating extends PlainMmMessage {
    public IEDeRegistrationType deRegistrationType;
    public IENasKeySetIdentifier ngKSI;
    public IE5gsMobileIdentity mobileIdentity;

    public DeRegistrationRequestUeOriginating() {
        super(EMessageType.DEREGISTRATION_REQUEST_UE_ORIGINATING);
    }

    public DeRegistrationRequestUeOriginating(IEDeRegistrationType deRegistrationType, IENasKeySetIdentifier ngKSI, IE5gsMobileIdentity mobileIdentity) {
        this();
        this.deRegistrationType = deRegistrationType;
        this.ngKSI = ngKSI;
        this.mobileIdentity = mobileIdentity;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("ngKSI", "deRegistrationType");
        builder.mandatoryIE("mobileIdentity");
    }
}
