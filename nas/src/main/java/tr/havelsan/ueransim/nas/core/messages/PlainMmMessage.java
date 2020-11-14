/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.core.messages;

import tr.havelsan.ueransim.nas.impl.enums.EExtendedProtocolDiscriminator;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;

public abstract class PlainMmMessage extends NasMessage {
    public ESecurityHeaderType securityHeaderType;
    public EMessageType messageType;

    public PlainMmMessage(EMessageType messageType) {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;

        this.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
        this.messageType = messageType;

        if (!messageType.isMobilityManagement())
            throw new IncorrectImplementationException("message type and super classes are inconsistent");
    }

    public final PlainMmMessage decodeMessage(OctetInputStream stream) {
        return (PlainMmMessage) decodeViaBuilder(stream);
    }

    public final void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        stream.writeOctet(securityHeaderType.intValue());
        stream.writeOctet(messageType.intValue());

        encodeViaBuilder(stream);
    }
}
