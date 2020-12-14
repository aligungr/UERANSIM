/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.l2.rlc.pdu;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class AmdPdu {
    public boolean dc;
    public boolean p;
    public int si;
    public int sn;
    public int so;
    public OctetString data;
}
