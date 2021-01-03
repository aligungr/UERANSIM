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
import tr.havelsan.ueransim.nas.impl.ies.IE5gMmCause;
import tr.havelsan.ueransim.nas.impl.ies.IEDeRegistrationType;
import tr.havelsan.ueransim.nas.impl.ies.IEGprsTimer2;

public class DeRegistrationRequestUeTerminated extends PlainMmMessage {
    public IEDeRegistrationType deRegistrationType;
    public IE5gMmCause mmCause;
    public IEGprsTimer2 t3346Value;

    public DeRegistrationRequestUeTerminated() {
        super(EMessageType.DEREGISTRATION_REQUEST_UE_TERMINATED);
    }

    public DeRegistrationRequestUeTerminated(IEDeRegistrationType deRegistrationType, IE5gMmCause mmCause, IEGprsTimer2 t3346Value) {
        this();
        this.deRegistrationType = deRegistrationType;
        this.mmCause = mmCause;
        this.t3346Value = t3346Value;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("deRegistrationType");
        builder.optionalIE(0x58, "mmCause");
        builder.optionalIE(0x5F, "t3346Value");
    }
}
