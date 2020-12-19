/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.interfaces;

import tr.havelsan.ueransim.app.link.rlc.entity.RlcEntity;
import tr.havelsan.ueransim.utils.octets.OctetString;

public interface IRlcConsumer {

    void deliverSdu(RlcEntity entity, OctetString sdu);

    void maxRetransmissionReached(RlcEntity entity);

    void sduSuccessfulDelivery(RlcEntity entity, int sduId);
}
