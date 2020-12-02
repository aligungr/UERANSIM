/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Value;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RrcEncoding {

    public static RRC_Value decodeUper(byte[] pdu, RrcDataUnitType type) {
        return RrcXerEncoder.decode(RrcJni.uperToXer(pdu, type), RRC_Value.class);
    }

    public static RRC_Value decodeUper(OctetString pdu, RrcDataUnitType type) {
        return decodeUper(pdu.toByteArray(), type);
    }

    public static byte[] encodeUper(RRC_Value value, RrcDataUnitType type) {
        return RrcJni.xerToUper(RrcXerEncoder.encode(value), type);
    }
}
