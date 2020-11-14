/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0;

import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_PDU;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NgapEncoding {

    public static NGAP_PDU decodeAper(byte[] pdu) {
        return (NGAP_PDU) decodeAper(pdu, NgapDataUnitType.NGAP_PDU);
    }

    public static NGAP_Value decodeAper(byte[] pdu, NgapDataUnitType type) {
        return NgapXerEncoder.decode(NgapJni.aperToXer(pdu, type), NGAP_PDU.class);
    }

    public static NGAP_PDU decodeAper(OctetString pdu) {
        return decodeAper(pdu.toByteArray());
    }

    public static NGAP_Value decodeAper(OctetString pdu, NgapDataUnitType type) {
        return decodeAper(pdu.toByteArray(), type);
    }

    public static byte[] encodeAper(NGAP_PDU pdu) {
        return NgapJni.xerToAper(NgapXerEncoder.encode(pdu), NgapDataUnitType.NGAP_PDU);
    }

    public static byte[] encodeAper(NGAP_Value value, NgapDataUnitType type) {
        return NgapJni.xerToAper(NgapXerEncoder.encode(value), type);
    }
}
