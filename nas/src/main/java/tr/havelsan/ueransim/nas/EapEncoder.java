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
 */

package tr.havelsan.ueransim.nas;

import tr.havelsan.ueransim.core.exceptions.NotImplementedException;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.eap.EapAttributes;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet2;

public class EapEncoder {

    /**
     * Encodes EAP PDU into given stream
     */
    public static void eapPdu(OctetOutputStream stream, Eap pdu) {
        var innerStream = new OctetOutputStream();

        innerStream.writeOctet(pdu.code.intValue());
        innerStream.writeOctet(pdu.id);

        if (pdu.EAPType == null) {
            innerStream.writeOctet2(4);
        } else {
            innerStream.writeOctet2(0); // dummy length
            innerStream.writeOctet(pdu.EAPType.intValue());

            if (pdu.EAPType.equals(Eap.EEapType.EAP_AKA_PRIME)) {
                encodeAKAPrime(innerStream, (EapAkaPrime) pdu);
            } else if (pdu.EAPType.equals(Eap.EEapType.NOTIFICATION)) {
                encodeNotification(innerStream);
            } else if (pdu.EAPType.equals(Eap.EEapType.IDENTITY)) {
                encodeIdentity(innerStream);
            } else {
                throw new NotImplementedException("eap type not implemented yet: " + pdu.EAPType.name());
            }

            var octets = innerStream.toOctetArray();
            var realLength = new Octet2(octets.length).toOctetArray();
            octets[2] = realLength[0];
            octets[3] = realLength[1];

            stream.writeOctets(octets);
        }
    }

    /**
     * Encodes EAP PDU.
     */
    public static byte[] eapPdu(Eap pdu) {
        var stream = new OctetOutputStream();
        eapPdu(stream, pdu);
        return stream.toByteArray();
    }

    private static void encodeNotification(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }

    private static void encodeIdentity(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }

    private static void encodeAKAPrime(OctetOutputStream stream, EapAkaPrime akaPrime) {
        stream.writeOctet(akaPrime.subType.intValue());
        stream.writeOctet2(0); // reserved 2-octet

        int c = 0;

        if (akaPrime.attributes == null) akaPrime.attributes = new EapAttributes();
        for (var entry : akaPrime.attributes.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            stream.writeOctet(key.intValue());
            stream.writeOctet((value.length + 2) / 4);
            stream.writeOctets(value.getAsOctetArray());

            c++;
        }
    }
}
