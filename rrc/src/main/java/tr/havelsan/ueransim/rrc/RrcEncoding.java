/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc;

import tr.havelsan.ueransim.asn.XerDecoder;
import tr.havelsan.ueransim.asn.XerEncoder;
import tr.havelsan.ueransim.asn.core.AsnValue;
import tr.havelsan.ueransim.rrc.asn.RRC_AsnMetaData;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RrcEncoding {

    private static final RRC_AsnMetaData metaData = null;
    private static final XerEncoder xerEncoder = new XerEncoder(metaData);
    private static final XerDecoder xerDecoder = new XerDecoder(metaData);

    public static AsnValue decodeUper(byte[] pdu, RrcDataUnitType type) {
        return xerDecoder.decode(RrcJni.uperToXer(pdu, type), type.getPodType());
    }

    public static AsnValue decodeUper(OctetString pdu, RrcDataUnitType type) {
        return decodeUper(pdu.toByteArray(), type);
    }

    public static byte[] encodeUper(AsnValue value, RrcDataUnitType type) {
        return RrcJni.xerToUper(xerEncoder.encode(value), type);
    }
}
