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
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
import tr.havelsan.ueransim.nas.impl.ies.IE5gMmCause;
import tr.havelsan.ueransim.nas.impl.ies.IEAuthenticationFailureParameter;

public class AuthenticationFailure extends PlainMmMessage {
    public IE5gMmCause mmCause;
    public IEAuthenticationFailureParameter authenticationFailureParameter;

    public AuthenticationFailure() {
        super(EMessageType.AUTHENTICATION_FAILURE);
    }

    public AuthenticationFailure(IE5gMmCause mmCause, IEAuthenticationFailureParameter authenticationFailureParameter) {
        this();
        this.mmCause = mmCause;
        this.authenticationFailureParameter = authenticationFailureParameter;
    }

    public AuthenticationFailure(EMmCause mmCause, IEAuthenticationFailureParameter authenticationFailureParameter) {
        this(new IE5gMmCause(mmCause), authenticationFailureParameter);
    }

    public AuthenticationFailure(EMmCause mmCause) {
        this(new IE5gMmCause(mmCause), null);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("mmCause");
        builder.optionalIE(0x30, "authenticationFailureParameter");
    }
}
