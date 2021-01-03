/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.exceptions.DecodingException;
import tr.havelsan.ueransim.utils.exceptions.NotImplementedException;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class EapDecoder {

    public static Eap eapPdu(OctetInputStream stream) {
        var code = decodeCode(stream);
        var id = decodeId(stream);
        var length = decodeLength(stream);

        if (length.intValue() == 4) {
            return new Eap(code, id, null);
        }

        var type = decodeEAPType(stream);

        int innerLength = length.intValue()
                - 1 // code
                - 1 // id
                - 2 // length
                - 1; // type

        Eap eap;
        if (type.equals(Eap.EEapType.EAP_AKA_PRIME)) {
            eap = decodeAKAPrime(stream, code, id, innerLength);
        } else if (type.equals(Eap.EEapType.NOTIFICATION)) {
            eap = decodeNotification(stream, code, id, innerLength);
        } else if (type.equals(Eap.EEapType.IDENTITY)) {
            eap = decodeIdentity(stream, code, id, innerLength);
        } else {
            throw new NotImplementedException("eap type not implemented yet: " + type.name());
        }

        return eap;
    }

    public static Eap eapPdu(OctetString pdu) {
        return eapPdu(new OctetInputStream(pdu.toByteArray()));
    }

    public static Eap eapPdu(String hex) {
        return eapPdu(new OctetString(hex));
    }

    private static Eap.ECode decodeCode(OctetInputStream stream) {
        return Eap.ECode.fromValue(stream.readOctetI());
    }

    private static Octet decodeId(OctetInputStream stream) {
        return new Octet(stream.readOctetI());
    }

    private static Octet2 decodeLength(OctetInputStream stream) {
        return new Octet2(stream.readOctet2I());
    }

    private static Eap.EEapType decodeEAPType(OctetInputStream data) {
        return Eap.EEapType.fromValue(data.readOctetI());
    }

    private static EapAkaPrime decodeAKAPrime(OctetInputStream stream, Eap.ECode code, Octet id, int length) {
        int readBytes = 0;

        // decode subtype
        var subType = decodeAKASubType(stream);
        readBytes += 1;

        var akaPrime = new EapAkaPrime(code, id, subType);

        // consume reserved 2 octets
        stream.readOctet2();
        readBytes += 2;

        while (readBytes < length) {
            // decode attribute type
            var type = decodeAKAAttributeType(stream);
            readBytes += 1;

            // decode attribute length
            var attributeLength = stream.readOctetI();
            readBytes += 1;

            // decode attribute value
            var attributeVal = stream.readOctetString(4 * attributeLength - 2);
            readBytes += 4 * attributeLength - 2;

            akaPrime.attributes.putRawAttribute(type, attributeVal);
        }

        if (readBytes != length)
            throw new DecodingException("readBytes exceeds the element length");

        return akaPrime;
    }

    private static ESubType decodeAKASubType(OctetInputStream stream) {
        return ESubType.fromValue(stream.readOctetI());
    }

    private static EAttributeType decodeAKAAttributeType(OctetInputStream stream) {
        return EAttributeType.fromValue(stream.readOctetI());
    }

    private static EapNotification decodeNotification(OctetInputStream stream, Eap.ECode code, Octet id, int length) {
        var res = new EapNotification(code, id);
        res.rawData = stream.readOctetString(length);
        return res;
    }

    private static EapIdentity decodeIdentity(OctetInputStream stream, Eap.ECode code, Octet id, int length) {
        var res = new EapIdentity(code, id);
        res.rawData = stream.readOctetString(length);
        return res;
    }
}
