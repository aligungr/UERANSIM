package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.impl.enums.EMobileCountryCode;
import com.runsim.backend.nas.impl.enums.EMobileNetworkCode;
import com.runsim.backend.nas.impl.enums.EProtectionSchemeIdentifier;
import com.runsim.backend.nas.impl.values.VHomeNetworkPki;
import com.runsim.backend.nas.impl.values.VMccMnc;
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
        var mccmnc = NasDecoder.nasValue(stream, VMccMnc.class);
        result.mcc = mccmnc.mcc;
        result.mnc = mccmnc.mnc;

        /* Decode routing indicator */
        int riLen = stream.peekOctetI(1) == 0xFF ? 1 : 2;
        result.routingIndicator = NasDecoder.bcdString(stream, riLen, false);
        if (riLen == 1) stream.readOctet();

        /* Decode protection schema id */
        result.protectionSchemaId = EProtectionSchemeIdentifier.fromValue(stream.readOctetI() & 0b1111);

        /* Decode home network public key identifier */
        result.homeNetworkPublicKeyIdentifier = NasDecoder.nasValue(stream, VHomeNetworkPki.class);

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
        var mccmnc = new VMccMnc();
        mccmnc.mcc = mcc;
        mccmnc.mnc = mnc;
        NasEncoder.nasValue(stream, mccmnc);

        /* Encode others */
        NasEncoder.bcdString(stream, routingIndicator, 2, false, null);
        stream.writeOctet(protectionSchemaId.value);
        stream.writeOctet(homeNetworkPublicKeyIdentifier.value);

        /* Encode schema output */
        if (protectionSchemaId.equals(EProtectionSchemeIdentifier.NULL_SCHEMA)) {
            NasEncoder.bcdString(stream, schemaOutput, -1, false, null);
        } else {
            stream.writeOctetString(new OctetString(schemaOutput));
        }
    }
}
