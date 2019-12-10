package tr.havelsan.ueransim.nas;

import tr.havelsan.ueransim.nas.eap.*;
import tr.havelsan.ueransim.exceptions.DecodingException;
import tr.havelsan.ueransim.exceptions.NotImplementedException;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;
import tr.havelsan.ueransim.nas.eap.*;

import java.util.LinkedHashMap;

public class EapDecoder {
    public static Eap eapPdu(OctetInputStream stream) {
        var code = decodeCode(stream);
        var id = decodeId(stream);
        var length = decodeLength(stream);
        var type = decodeEAPType(stream);

        int innerLength = length.intValue()
                - 1 // code
                - 1 // id
                - 2 // length
                - 1; // type

        Eap eap;
        if (type.equals(EEapType.EAP_AKA_PRIME)) {
            eap = decodeAKAPrime(stream, innerLength);
        } else if (type.equals(EEapType.NOTIFICATION)) {
            eap = decodeNotification(stream, innerLength);
        } else if (type.equals(EEapType.IDENTITY)) {
            eap = decodeIdentity(stream, innerLength);
        } else {
            throw new NotImplementedException("eap type not implemented yet: " + type.name());
        }

        eap.code = code;
        eap.id = id;
        eap.length = length;
        eap.EAPType = type;
        return eap;
    }

    private static EEapCode decodeCode(OctetInputStream stream) {
        return EEapCode.fromValue(stream.readOctetI());
    }

    private static Octet decodeId(OctetInputStream stream) {
        return new Octet(stream.readOctetI());
    }

    private static Octet2 decodeLength(OctetInputStream stream) {
        return new Octet2(stream.readOctet2I());
    }

    private static EEapType decodeEAPType(OctetInputStream data) {
        return EEapType.fromValue(data.readOctetI());
    }

    private static EapAkaPrime decodeAKAPrime(OctetInputStream stream, int length) {
        var akaPrime = new EapAkaPrime();
        akaPrime.attributes = new LinkedHashMap<>();

        int readBytes = 0;

        // decode subtype
        akaPrime.subType = decodeAKASubType(stream);
        readBytes += 1;

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

            akaPrime.attributes.put(type, attributeVal);
        }

        if (readBytes != length)
            throw new DecodingException("readBytes exceeds the element length");

        return akaPrime;
    }

    private static EEapAkaSubType decodeAKASubType(OctetInputStream stream) {
        return EEapAkaSubType.fromValue(stream.readOctetI());
    }

    private static EEapAkaAttributeType decodeAKAAttributeType(OctetInputStream stream) {
        return EEapAkaAttributeType.fromValue(stream.readOctetI());
    }

    private static EapNotification decodeNotification(OctetInputStream stream, int length) {
        var res = new EapNotification();
        res.rawData = stream.readOctetString(length);
        return res;
    }

    private static EapIdentity decodeIdentity(OctetInputStream stream, int length) {
        var res = new EapIdentity();
        res.rawData = stream.readOctetString(length);

        // TODO: HACK: AMF bugını geçici olarak çözmek için
        if (stream.remaining() == 3 && stream.peekOctetString(3).equals(new OctetString("020000"))) {
            stream.readOctetString(3);
        }

        return res;
    }
}
