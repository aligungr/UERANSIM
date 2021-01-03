/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.nas.impl.enums.EIdentityType;
import tr.havelsan.ueransim.nas.impl.enums.ESupiFormat;
import tr.havelsan.ueransim.nas.impl.values.V5gTmsi;
import tr.havelsan.ueransim.nas.impl.values.VAmfSetId;
import tr.havelsan.ueransim.nas.impl.values.VHomeNetworkPki;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;
import tr.havelsan.ueransim.utils.exceptions.ReservedOrInvalidValueException;

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
                var mccmnc = new VPlmn().decode(stream);
                result.mcc = mccmnc.mcc;
                result.mnc = mccmnc.mnc;

                /* Decode routing indicator */
                int riLen = stream.peekOctetI(1) == 0xFF ? 1 : 2;
                result.routingIndicator = NasDecoder.bcdString(stream, riLen, false);
                if (riLen == 1) stream.readOctet();

                /* Decode protection schema id */
                result.protectionSchemaId = IEImsiMobileIdentity.EProtectionSchemeIdentifier.fromValue(stream.readOctetI() & 0b1111);

                /* Decode home network public key identifier */
                result.homeNetworkPublicKeyIdentifier = new VHomeNetworkPki().decode(stream);

                /* Decode schema output */
                String schemaOutput;
                if (result.protectionSchemaId.equals(IEImsiMobileIdentity.EProtectionSchemeIdentifier.NULL_SCHEME)) {
                    result.schemeOutput = NasDecoder.bcdString(stream, length - 7, false);
                } else {
                    var range = stream.readOctetString(length - 7);
                    result.schemeOutput = range.toHexString();
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
            var mccmnc = new VPlmn().decode(stream);
            result.mcc = mccmnc.mcc;
            result.mnc = mccmnc.mnc;

            /* Decode others */
            result.amfRegionId = stream.readOctet();
            result.amfSetId = new VAmfSetId().decode(stream);
            result.amfPointer = new Bit6(stream.readOctetI());
            result.tmsi = new V5gTmsi().decode(stream);

            return result;
        } else if (typeOfIdentity.equals(EIdentityType.TMSI)) {
            stream.readOctetI();

            var res = new IE5gTmsiMobileIdentity();
            res.amfSetId = new VAmfSetId().decode(stream);
            res.amfPointer = new Bit6(stream.readOctetI());
            res.tmsi = new V5gTmsi().decode(stream);
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
