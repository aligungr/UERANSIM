/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.l2.rlc.pdu;

import tr.havelsan.ueransim.app.l2.rlc.RlcConstants;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UmdPdu {
    public int si;
    public int so;
    public int sn;
    public OctetString data;

    public static UmdPdu decode(OctetInputStream stream, boolean isShortSn) {
        var octet = stream.readOctet();

        var umd = new UmdPdu();
        umd.si = octet.getBitRangeI(6, 7);

        if (umd.si != RlcConstants.SI_FULL) {
            if (isShortSn) {
                umd.sn = octet.getBitRangeI(0, 5);
            } else {
                umd.sn = octet.getBitRangeI(0, 3);
                umd.sn <<= 8;
                umd.sn |= octet.getBitRangeI(0, 7);
            }

            if (umd.si != RlcConstants.SI_FIRST) {
                umd.so = stream.readOctet2I();
            }
        }

        umd.data = stream.readOctetString();
        return umd;
    }
}
