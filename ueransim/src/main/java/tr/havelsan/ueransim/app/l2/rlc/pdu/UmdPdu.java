/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.l2.rlc.pdu;

import tr.havelsan.ueransim.app.l2.rlc.RlcConstants;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UmdPdu {
    public int si;
    public int so;
    public int sn;
    public OctetString data;

    // Indicates whether this SDU is delivered.
    //  If the SDU has fragmentation, ALL UmdPdu which has the same underlying SDU must be
    //  marked as _isDelivered=true at the reassembling and delivering operation.
    public boolean _isProcessed;

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

    public static void encode(OctetOutputStream stream, UmdPdu pdu, boolean isShortSn) {
        var octet0 = new Octet()
                .setBitRange(6, 7, pdu.si);

        int remainingSn = -1;

        if (pdu.si != RlcConstants.SI_FULL) {
            if (isShortSn) {
                octet0 = octet0.setBitRange(0, 5, pdu.sn);
            } else {
                octet0 = octet0.setBitRange(0, 3, pdu.sn >> 8);
                remainingSn = pdu.sn & 0b11111111;
            }
        }

        stream.writeOctet(octet0);

        if (remainingSn != -1)
            stream.writeOctet(remainingSn);

        if (pdu.si == RlcConstants.SI_MIDDLE || pdu.si == RlcConstants.SI_LAST)
            stream.writeOctet2(pdu.so);

        stream.writeOctetString(pdu.data);
    }
}
