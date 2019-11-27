package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.IncorrectImplementationException;
import com.runsim.backend.exceptions.ReservedOrInvalidValueException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.enums.EIdentityType;
import com.runsim.backend.nas.impl.enums.ESupiFormat;
import com.runsim.backend.nas.impl.values.V5gTmsi;
import com.runsim.backend.nas.impl.values.VAmfSetId;
import com.runsim.backend.nas.impl.values.VHomeNetworkPki;
import com.runsim.backend.nas.impl.values.VPlmn;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit6;

import java.nio.charset.StandardCharsets;

public class IE5gsMobileIdentity extends InformationElement6 {

    @Override
    protected final IE5gsMobileIdentity decodeIE6(OctetInputStream stream, int length) {
        int flags = stream.peekOctetI();

        var typeOfIdentity = EIdentityType.fromValue(flags & 0b111);
        boolean isEven = ((flags >> 3) & 0b1) == 0;

        if (typeOfIdentity.equals(EIdentityType.SUCI)) {
            stream.readOctetI();

            var supiFormat = ESupiFormat.fromValue((flags >> 4) & 0b111);

            if (supiFormat.equals(ESupiFormat.IMSI)) {
                length--;

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
                result.protectionSchemaId = IEImsiMobileIdentity.EProtectionSchemeIdentifier.fromValue(stream.readOctetI() & 0b1111);

                /* Decode home network public key identifier */
                result.homeNetworkPublicKeyIdentifier = VHomeNetworkPki.decode(stream);

                /* Decode schema output */
                String schemaOutput;
                if (result.protectionSchemaId.equals(IEImsiMobileIdentity.EProtectionSchemeIdentifier.NULL_SCHEMA)) {
                    result.schemaOutput = NasDecoder.bcdString(stream, length - 7, false);
                } else {
                    var range = stream.readOctetString(length - 7);
                    result.schemaOutput = range.toHexString();
                }

                return result;
            } else if (supiFormat.equals(ESupiFormat.NETWORK_SPECIFIC_IDENTIFIER)) {
                var res = new IENsiMobileIdentity();
                res.suciNai = new String(stream.readOctetArrayB(length - 1), StandardCharsets.UTF_8);
                return res;
            } else {
                throw new ReservedOrInvalidValueException(ESupiFormat.class);
            }
        } else if (typeOfIdentity.equals(EIdentityType.IMEI)) {
            var imeiMobileIdentity = new IEImeiMobileIdentity();
            imeiMobileIdentity.imei = NasDecoder.bcdString(stream, length, true);
            return imeiMobileIdentity;
        } else if (typeOfIdentity.equals(EIdentityType.GUTI)) {
            stream.readOctet();

            var result = new IE5gGutiMobileIdentity();

            /* Decode MCC and MNC */
            var mccmnc = VPlmn.decode(stream);
            result.mcc = mccmnc.mcc;
            result.mnc = mccmnc.mnc;

            /* Decode others */
            result.amfRegionId = stream.readOctet();
            result.amfSetId = VAmfSetId.decode(stream);
            result.amfPointer = new Bit6(stream.readOctetI());
            result.tmsi = V5gTmsi.decode(stream);

            return result;
        } else if (typeOfIdentity.equals(EIdentityType.TMSI)) {
            var res = new IE5gTmsiMobileIdentity();
            res.amfSetId = VAmfSetId.decode(stream);
            res.amfPointer = new Bit6(stream.readOctetI());
            res.tmsi = V5gTmsi.decode(stream);
            return res;
        } else if (typeOfIdentity.equals(EIdentityType.IMEISV)) {
            var res = new IEImeiSvMobileIdentity();
            res.imeiSv = NasDecoder.bcdString(stream, length, true);
            return res;
        } else if (typeOfIdentity.equals(EIdentityType.NO_IDENTITY)) {
            return new IENoIdentity();
        } else {
            throw new ReservedOrInvalidValueException("Type of Identity", typeOfIdentity);
        }
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new IncorrectImplementationException("sub types must override this method.");
    }
}
