/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.core.messages;

import tr.havelsan.ueransim.nas.impl.enums.EExtendedProtocolDiscriminator;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.nas.impl.enums.EProcedureTransactionIdentity;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;

public abstract class PlainSmMessage extends NasMessage {
    public EPduSessionIdentity pduSessionId;
    public EProcedureTransactionIdentity pti;
    public EMessageType messageType;

    public PlainSmMessage(EMessageType messageType) {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.SESSION_MANAGEMENT_MESSAGES;

        this.pduSessionId = EPduSessionIdentity.NO_VAL;
        this.pti = EProcedureTransactionIdentity.NO_VAL;
        this.messageType = messageType;

        if (messageType.isMobilityManagement())
            throw new IncorrectImplementationException("message type and super classes are inconsistent");
    }

    public final PlainSmMessage decodeMessage(OctetInputStream stream) {
        return (PlainSmMessage) decodeViaBuilder(stream);
    }

    public final void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        stream.writeOctet(pduSessionId.intValue());
        stream.writeOctet(pti.intValue());
        stream.writeOctet(messageType.intValue());

        encodeViaBuilder(stream);
    }
}
