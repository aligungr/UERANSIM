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
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.ies.IEPduSessionReactivationResult;
import tr.havelsan.ueransim.nas.impl.ies.IEPduSessionReactivationResultErrorCause;
import tr.havelsan.ueransim.nas.impl.ies.IEPduSessionStatus;

public class ServiceAccept extends PlainMmMessage {
    public IEPduSessionStatus pduSessionStatus;
    public IEPduSessionReactivationResult pduSessionReactivationResult;
    public IEPduSessionReactivationResultErrorCause pduSessionReactivationResultErrorCause;
    public IEEapMessage eapMessage;

    public ServiceAccept() {
        super(EMessageType.SERVICE_ACCEPT);
    }

    public ServiceAccept(IEPduSessionStatus pduSessionStatus, IEPduSessionReactivationResult pduSessionReactivationResult, IEPduSessionReactivationResultErrorCause pduSessionReactivationResultErrorCause, IEEapMessage eapMessage) {
        this();
        this.pduSessionStatus = pduSessionStatus;
        this.pduSessionReactivationResult = pduSessionReactivationResult;
        this.pduSessionReactivationResultErrorCause = pduSessionReactivationResultErrorCause;
        this.eapMessage = eapMessage;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x50, "pduSessionStatus");
        builder.optionalIE(0x26, "pduSessionReactivationResult");
        builder.optionalIE(0x72, "pduSessionReactivationResultErrorCause");
        builder.optionalIE(0x78, "eapMessage");
    }
}
