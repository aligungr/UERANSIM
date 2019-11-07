package com.runsim.backend.nas;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.eap.*;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet2;

import java.util.HashMap;

/* package-private */
class EapDecoder {
    private final OctetInputStream data;

    public EapDecoder(byte[] data) {
        this(new OctetInputStream(data));
    }

    public EapDecoder(OctetInputStream data) {
        this.data = data;
    }

    public ExtensibleAuthenticationProtocol decodeEAP() {
        var code = decodeCode();
        var id = decodeId();
        var length = decodeLength();
        var type = decodeEAPType();

        int innerLength = length.intValue
                - 1 // code
                - 1 // id
                - 2 // length
                - 1; // type

        ExtensibleAuthenticationProtocol eap;
        if (type.equals(EEapType.EAP_AKA_PRIME)) {
            eap = decodeAKAPrime(innerLength);
        } else if (type.equals(EEapType.NOTIFICATION)) {
            eap = decodeNotification(innerLength);
        } else {
            throw new NotImplementedException("eap type not implemented yet: " + type.name);
        }

        eap.code = code;
        eap.id = id;
        eap.length = length;
        eap.EAPType = type;
        return eap;
    }

    private ECode decodeCode() {
        var val = ECode.fromValue(data.readOctetI());
        if (val == null) throw new InvalidValueException(ECode.class);
        return val;
    }

    private Octet decodeId() {
        return new Octet(data.readOctetI());
    }

    private Octet2 decodeLength() {
        return new Octet2(data.readOctet2I());
    }

    private EEapType decodeEAPType() {
        var val = EEapType.fromValue(data.readOctetI());
        if (val == null) throw new InvalidValueException(EEapType.class);
        return val;
    }

    private AkaPrime decodeAKAPrime(int length) {
        var akaPrime = new AkaPrime();
        akaPrime.attributes = new HashMap<>();

        int readBytes = 0;

        // decode subtype
        akaPrime.subType = decodeAKASubType();
        readBytes += 1;

        // consume reserved 2 octets
        data.readOctet2();
        readBytes += 2;

        while (readBytes < length) {
            // decode attribute type
            var type = decodeAKAAttributeType();
            readBytes += 1;

            // decode attribute length
            var attributeLength = data.readOctetI();
            readBytes += 1;

            // decode attribute value
            var attributeVal = data.readOctetString(4 * attributeLength - 2);
            readBytes += 4 * attributeLength - 2;

            akaPrime.attributes.put(type, attributeVal);
        }

        if (readBytes != length)
            throw new DecodingException("readBytes exceeds the element length");

        return akaPrime;
    }

    private EAkaSubType decodeAKASubType() {
        var val = EAkaSubType.fromValue(data.readOctetI());
        if (val == null) throw new InvalidValueException(EAkaSubType.class);
        return val;
    }

    private EAkaAttributeType decodeAKAAttributeType() {
        var val = EAkaAttributeType.fromValue(data.readOctetI());
        if (val == null) throw new InvalidValueException(EAkaAttributeType.class);
        return val;
    }

    private Notification decodeNotification(int length) {
        throw new NotImplementedException("eap notification not implemented yet");
    }
}
