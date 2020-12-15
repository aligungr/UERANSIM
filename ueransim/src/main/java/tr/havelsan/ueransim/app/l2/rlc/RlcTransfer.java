/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.l2.rlc;

import tr.havelsan.ueransim.app.l2.rlc.entity.RlcEntity;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RlcTransfer {

    public static void deliverSdu(RlcEntity entity, OctetString data) {
        // Deliver SDU to upper layer (PDCP)
    }

}
