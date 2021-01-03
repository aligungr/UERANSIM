/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.nas.impl.values.VHomeNetworkPki;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEImsiMobileIdentity extends IESuciMobileIdentity {
    public EMccValue mcc;
    public EMncValue mnc;
    public String routingIndicator;
    public EProtectionSchemeIdentifier protectionSchemaId;
    public VHomeNetworkPki homeNetworkPublicKeyIdentifier;
    public String schemeOutput;

    public IEImsiMobileIdentity() {
    }

    public IEImsiMobileIdentity(EMccValue mcc, EMncValue mnc, String routingIndicator, EProtectionSchemeIdentifier protectionSchemaId, VHomeNetworkPki homeNetworkPublicKeyIdentifier, String schemeOutput) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.routingIndicator = routingIndicator;
        this.protectionSchemaId = protectionSchemaId;
        this.homeNetworkPublicKeyIdentifier = homeNetworkPublicKeyIdentifier;
        this.schemeOutput = schemeOutput;
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
        if (protectionSchemaId.equals(EProtectionSchemeIdentifier.NULL_SCHEME)) {
            NasEncoder.bcdString(stream, schemeOutput, -1, false, null);
        } else {
            stream.writeOctetString(new OctetString(schemeOutput));
        }
    }

    public static class EProtectionSchemeIdentifier extends ProtocolEnum {
        public static final EProtectionSchemeIdentifier NULL_SCHEME
                = new EProtectionSchemeIdentifier(0b0000, "Null scheme");
        public static final EProtectionSchemeIdentifier ECIES_SCHEME_PROFILE_A
                = new EProtectionSchemeIdentifier(0b0001, "ECIES scheme profile A");
        public static final EProtectionSchemeIdentifier ECIES_SCHEME_PROFILE_B
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
