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
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.ies.IEGprsTimer2;
import tr.havelsan.ueransim.nas.impl.ies.IEPduSessionStatus;

public class ServiceReject extends PlainMmMessage {
    public IE5gMmCause mmCause;
    public IEPduSessionStatus pduSessionStatus;
    public IEGprsTimer2 t3346Value;
    public IEEapMessage eapMessage;

    public ServiceReject() {
        super(EMessageType.SERVICE_REJECT);
    }

    public ServiceReject(IE5gMmCause mmCause, IEPduSessionStatus pduSessionStatus, IEGprsTimer2 t3346Value, IEEapMessage eapMessage) {
        this();
        this.mmCause = mmCause;
        this.pduSessionStatus = pduSessionStatus;
        this.t3346Value = t3346Value;
        this.eapMessage = eapMessage;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("mmCause");
        builder.optionalIE(0x50, "pduSessionStatus");
        builder.optionalIE(0x5f, "t3346Value");
        builder.optionalIE(0x78, "eapMessage");
    }
}
