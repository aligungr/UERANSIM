/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas;

import tr.havelsan.ueransim.nas.core.ies.*;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit4;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NasEncoder {

    /**
     * Encodes NAS/5GS PDU into byte array
     */
    public static byte[] nasPdu(NasMessage pdu) {
        var stream = new OctetOutputStream();
        pdu.encodeMessage(stream);
        return stream.toByteArray();
    }

    /**
     * Encodes NAS/5GS PDU into octet string
     */
    public static OctetString nasPduS(NasMessage pdu) {
        return pdu == null ? null : new OctetString(nasPdu(pdu));
    }

    /**
     * Encodes information element type 1
     */
    public static void ie1(OctetOutputStream stream, InformationElement1 big, InformationElement1 little) {
        int bigHalf = big.encodeIE1() & 0xF;
        int littleHalf = little.encodeIE1() & 0xF;
        stream.writeOctet(bigHalf, littleHalf);
    }

    /**
     * Encodes information element type 1
     */
    public static void ie1(OctetOutputStream stream, Bit4 big, InformationElement1 little) {
        int bigHalf = big.intValue();
        int littleHalf = little.encodeIE1() & 0xF;
        stream.writeOctet(bigHalf, littleHalf);
    }

    /**
     * Encodes information element type 1
     */
    public static void ie1(OctetOutputStream stream, InformationElement1 big, Bit4 little) {
        int bigHalf = big.encodeIE1() & 0xF;
        int littleHalf = little.intValue();
        stream.writeOctet(bigHalf, littleHalf);
    }

    /**
     * Encodes information element type 2,3,4,6 without IEI. Length is automatically inserted at the start
     * as if the type is 4 or 6.
     */
    public static void ie2346(OctetOutputStream stream, InformationElement ie) {
        if (ie == null) throw new IllegalArgumentException();
        if (ie instanceof InformationElement1) throw new IllegalArgumentException();

        var newStream = new OctetOutputStream();

        if (ie instanceof InformationElement2) {
            ((InformationElement2) ie).encodeIE2(stream);
        } else if (ie instanceof InformationElement3) {
            ((InformationElement3) ie).encodeIE3(stream);
        } else if (ie instanceof InformationElement4) {
            ((InformationElement4) ie).encodeIE4(newStream);
            stream.writeOctet(newStream.length());
            stream.writeStream(newStream);
        } else if (ie instanceof InformationElement6) {
            ((InformationElement6) ie).encodeIE6(newStream);
            stream.writeOctet2(newStream.length());
            stream.writeStream(newStream);
        } else {
            throw new IllegalArgumentException("bad ie type");
        }
    }

    /**
     * Encodes information element type 2,3,4,6 with IEI. Length is automatically inserted at the start
     * as if the type is 4 or 6.
     */
    public static void ie2346(OctetOutputStream stream, int iei, InformationElement ie) {
        stream.writeOctet(iei);
        ie2346(stream, ie);
    }

    /**
     * Encodes information element type 2,3,4,6 with IEI. Length is automatically inserted at the start
     * as if the type is 4 or 6.
     */
    public static void ie2346(OctetOutputStream stream, Octet iei, InformationElement ie) {
        stream.writeOctet(iei);
        ie2346(stream, ie);
    }

    /**
     * Encodes BCD (binary coded decimal) value.
     * This method is not battle tested, and may contain errors.
     *
     * @param stream           the relevant stream
     * @param octetLength      maximum octet length when encoding BCD string to octet string,
     *                         or pass <code>-1</code> to perform minimum number of octets
     * @param skipFirst        true if the first half octet should be skipped.
     * @param skippedHalfOctet if <code>skipFirst</code>is true, then <code>skippedHalfOctet</code> is set to the skipped half octet.
     *                         if <code>skippedHalfOctet</code> is null, then zero is assumed.
     */
    public static void bcdString(OctetOutputStream stream, String bcd, int octetLength, boolean skipFirst, Bit4 skippedHalfOctet) {
        int requiredHalfOctets = bcd.length();
        if (skipFirst) requiredHalfOctets++;
        if (requiredHalfOctets % 2 != 0) requiredHalfOctets++;

        int requiredOctets = requiredHalfOctets / 2;
        if (octetLength == -1)
            octetLength = requiredOctets;
        if (octetLength < requiredOctets)
            throw new IllegalArgumentException("octetLength should be greater or equal to " + requiredOctets);

        int[] halfOctets = new int[requiredHalfOctets];
        for (int i = 0; i < bcd.length(); i++) {
            char c = bcd.charAt(i);
            if (c < '0' || c > '9')
                throw new IllegalArgumentException("BCD string contains invalid characters");
            halfOctets[i] = c - '0';
        }

        if (skipFirst) {
            for (int i = bcd.length(); i >= 1; i--)
                halfOctets[i] = halfOctets[i - 1];
            halfOctets[0] = skippedHalfOctet != null ? skippedHalfOctet.intValue() : 0;
        }

        int spare = requiredHalfOctets - (bcd.length() + (skipFirst ? 1 : 0));
        for (int i = 0; i < spare; i++) {
            halfOctets[i + bcd.length() + (skipFirst ? 1 : 0)] = 0xF;
        }

        for (int i = 0; i < halfOctets.length / 2; i++) {
            int little = halfOctets[2 * i];
            int big = halfOctets[2 * i + 1];
            int octet = big << 4 | little;
            stream.writeOctet(octet);
        }
    }
}
