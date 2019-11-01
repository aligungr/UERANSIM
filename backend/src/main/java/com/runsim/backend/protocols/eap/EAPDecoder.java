package com.runsim.backend.protocols.eap;

import com.runsim.backend.protocols.OctetInputStream;
import com.runsim.backend.protocols.exceptions.DecodingException;
import com.runsim.backend.protocols.exceptions.InvalidValueException;
import com.runsim.backend.protocols.exceptions.NotImplementedException;
import com.runsim.backend.protocols.octets.Octet;
import com.runsim.backend.protocols.octets.Octet2;
import com.runsim.backend.protocols.octets.OctetString;

import java.util.HashMap;

public class EAPDecoder {
    private final OctetInputStream data;

    public EAPDecoder(byte[] data) {
        this(new OctetInputStream(data));
    }

    public EAPDecoder(OctetInputStream data) {
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
        if (type.equals(EAPType.EAP_AKA_PRIME)) {
            eap = decodeAKAPrime(innerLength);
        } else {
            throw new NotImplementedException("eap type not implemented yet: " + type.name);
        }

        eap.code = code;
        eap.id = id;
        eap.length = length;
        eap.EAPType = type;
        return eap;
    }

    private Code decodeCode() {
        var val = Code.fromValue(data.readOctet());
        if (val == null) throw new InvalidValueException(Code.class);
        return val;
    }

    private Octet decodeId() {
        return new Octet(data.readOctet());
    }

    private Octet2 decodeLength() {
        return new Octet2(data.readOctet2());
    }

    private EAPType decodeEAPType() {
        var val = EAPType.fromValue(data.readOctet());
        if (val == null) throw new InvalidValueException(EAPType.class);
        return val;
    }

    private AKAPrime decodeAKAPrime(int length) {
        var akaPrime = new AKAPrime();
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
            var attributeLength = data.readOctet();
            readBytes += 1;

            // decode attribute value
            var attributeVal = data.readOctets(4 * attributeLength - 2);
            readBytes += 4 * attributeLength - 2;

            akaPrime.attributes.put(type, new OctetString(attributeVal));
        }

        if (readBytes != length)
            throw new DecodingException("readBytes exceeds the element length");

        return akaPrime;
    }

    private AKASubType decodeAKASubType() {
        var val = AKASubType.fromValue(data.readOctet());
        if (val == null) throw new InvalidValueException(AKASubType.class);
        return val;
    }

    private AKAAttributeType decodeAKAAttributeType() {
        var val = AKAAttributeType.fromValue(data.readOctet());
        if (val == null) throw new InvalidValueException(AKAAttributeType.class);
        return val;
    }
}
