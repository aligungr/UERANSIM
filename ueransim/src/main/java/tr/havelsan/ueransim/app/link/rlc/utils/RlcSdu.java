/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class RlcSdu {
    public final int sduId;
    public final OctetString data;
    public int sn;
    public int retransmissionCount;

    public RlcSdu(int sduId, OctetString data) {
        this.sduId = sduId;
        this.data = data;
    }
}
