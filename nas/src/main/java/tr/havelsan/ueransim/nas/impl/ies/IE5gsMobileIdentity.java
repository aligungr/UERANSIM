/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.core.exceptions.IncorrectImplementationException;
import tr.havelsan.ueransim.core.exceptions.ReservedOrInvalidValueException;
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
