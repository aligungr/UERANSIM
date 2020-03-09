package tr.havelsan.ueransim.nas;

import tr.havelsan.ueransim.exceptions.DecodingException;
import tr.havelsan.ueransim.exceptions.NotImplementedException;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.eap.EapIdentity;
import tr.havelsan.ueransim.nas.eap.EapNotification;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedHashMap;

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
        var akaPrime = new EapAkaPrime(code, id);
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

    private static EapAkaPrime.ESubType decodeAKASubType(OctetInputStream stream) {
        return EapAkaPrime.ESubType.fromValue(stream.readOctetI());
    }

    private static EapAkaPrime.EAttributeType decodeAKAAttributeType(OctetInputStream stream) {
        return EapAkaPrime.EAttributeType.fromValue(stream.readOctetI());
    }

    private static EapNotification decodeNotification(OctetInputStream stream, Eap.ECode code, Octet id, int length) {
        var res = new EapNotification(code, id);
        res.rawData = stream.readOctetString(length);
        return res;
    }

    private static EapIdentity decodeIdentity(OctetInputStream stream, Eap.ECode code, Octet id, int length) {
        var res = new EapIdentity(code, id);
        res.rawData = stream.readOctetString(length);

        ////// TODO: HACK: AMF bugını geçici olarak çözmek için
        ////if (stream.remaining() == 3 && stream.peekOctetString(3).equals(new OctetString("020000"))) {
        ////    stream.readOctetString(3);
        ////}

        return res;
    }
}
