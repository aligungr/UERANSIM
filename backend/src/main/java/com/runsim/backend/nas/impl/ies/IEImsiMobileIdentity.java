package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.impl.enums.EMobileCountryCode;
import com.runsim.backend.nas.impl.enums.EMobileNetworkCode;
import com.runsim.backend.nas.impl.values.VHomeNetworkPki;
import com.runsim.backend.nas.impl.values.VPlmn;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

public class IEImsiMobileIdentity extends IESuciMobileIdentity {
    public EMobileCountryCode mcc;
    public EMobileNetworkCode mnc;
    public String routingIndicator;
    public EProtectionSchemeIdentifier protectionSchemaId;
    public VHomeNetworkPki homeNetworkPublicKeyIdentifier;
    public String schemaOutput;

    @Override
    public IEImsiMobileIdentity decodeMobileIdentity(OctetInputStream stream, int length, boolean isEven) {
        var result = new IEImsiMobileIdentity();

        /* Decode MCC */
        var mccmnc = VPlmn.decode(stream);
        result.mcc = mccmnc.mcc;
        result.mnc = mccmnc.mnc;

        /* Decode routing indicator */
        int riLen = stream.peekOctetI(1) == 0xFF ? 1 : 2;
        result.routingIndicator = NasDecoder.bcdString(stream, riLen, false);
        if (riLen == 1) stream.readOctet();

        /* Decode protection schema id */
        result.protectionSchemaId = EProtectionSchemeIdentifier.fromValue(stream.readOctetI() & 0b1111);

        /* Decode home network public key identifier */
        result.homeNetworkPublicKeyIdentifier = VHomeNetworkPki.decode(stream);

        /* Decode schema output */
        String schemaOutput;
        if (result.protectionSchemaId.equals(EProtectionSchemeIdentifier.NULL_SCHEMA)) {
            result.schemaOutput = NasDecoder.bcdString(stream, length - 7, false);
        } else {
            var range = stream.readOctetString(length - 7);
            result.schemaOutput = range.toHexString();
        }

        return result;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0x01); // Flags for SUCI with SUPI format IMSI

        /* Encode MCC and MNC*/
        var mccmnc = new VPlmn();
        mccmnc.mcc = mcc;
        mccmnc.mnc = mnc;
        mccmnc.encode(stream);

        /* Encode others */
        NasEncoder.bcdString(stream, routingIndicator, 2, false, null);
        stream.writeOctet(protectionSchemaId.intValue());
        stream.writeOctet(homeNetworkPublicKeyIdentifier.value);

        /* Encode schema output */
        if (protectionSchemaId.equals(EProtectionSchemeIdentifier.NULL_SCHEMA)) {
            NasEncoder.bcdString(stream, schemaOutput, -1, false, null);
        } else {
            stream.writeOctetString(new OctetString(schemaOutput));
        }
    }

    public static class EProtectionSchemeIdentifier extends ProtocolEnum {
        public static final EProtectionSchemeIdentifier NULL_SCHEMA
                = new EProtectionSchemeIdentifier(0b0000, "Null scheme");
        public static final EProtectionSchemeIdentifier ECIES_SCHEMA_PROFILE_A
                = new EProtectionSchemeIdentifier(0b0001, "ECIES scheme profile A");
        public static final EProtectionSchemeIdentifier ECIES_SCHEMA_PROFILE_B
                = new EProtectionSchemeIdentifier(0b0010, "ECIES scheme profile B");

        //public static final EProtectionSchemeIdentifier RESERVED1
        //        = new EProtectionSchemeIdentifier(0b0011, "Reserved");
        //public static final EProtectionSchemeIdentifier RESERVED2
        //        = new EProtectionSchemeIdentifier(0b0100, "Reserved");
        //public static final EProtectionSchemeIdentifier RESERVED3
        //        = new EProtectionSchemeIdentifier(0b0101, "Reserved");
        //public static final EProtectionSchemeIdentifier RESERVED4
        //        = new EProtectionSchemeIdentifier(0b0110, "Reserved");
        //public static final EProtectionSchemeIdentifier RESERVED5
        //        = new EProtectionSchemeIdentifier(0b0111, "Reserved");
        //public static final EProtectionSchemeIdentifier RESERVED6
        //        = new EProtectionSchemeIdentifier(0b1000, "Reserved");
        //public static final EProtectionSchemeIdentifier RESERVED7
        //        = new EProtectionSchemeIdentifier(0b1001, "Reserved");
        //public static final EProtectionSchemeIdentifier RESERVED8
        //        = new EProtectionSchemeIdentifier(0b1010, "Reserved");
        //public static final EProtectionSchemeIdentifier RESERVED9
        //        = new EProtectionSchemeIdentifier(0b1011, "Reserved");

        public static final EProtectionSchemeIdentifier OPERATOR_SPECIFIC1
                = new EProtectionSchemeIdentifier(0b1100, "Operator-specific protection scheme");
        public static final EProtectionSchemeIdentifier OPERATOR_SPECIFIC2
                = new EProtectionSchemeIdentifier(0b1101, "Operator-specific protection scheme");
        public static final EProtectionSchemeIdentifier OPERATOR_SPECIFIC3
                = new EProtectionSchemeIdentifier(0b1110, "Operator-specific protection scheme");
        public static final EProtectionSchemeIdentifier OPERATOR_SPECIFIC4
                = new EProtectionSchemeIdentifier(0b1111, "Operator-specific protection scheme");

        private EProtectionSchemeIdentifier(int value, String name) {
            super(value, name);
        }

        public static EProtectionSchemeIdentifier fromValue(int value) {
            return fromValueGeneric(EProtectionSchemeIdentifier.class, value, null);
        }

        //public boolean isReserved() {
        //    return value >= RESERVED1.value && value <= RESERVED9.value;
        //}

        public boolean isOperatorSpecific() {
            return value >= OPERATOR_SPECIFIC1.value && value <= OPERATOR_SPECIFIC4.value;
        }
    }
}
