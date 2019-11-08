package com.runsim.backend.nas;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.ies.*;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit4;
import com.runsim.backend.utils.octets.Octet;

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
            throw new NotImplementedException("");
        } else if (ie instanceof InformationElement3) {
            throw new NotImplementedException("");
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
     * @param octetLength maximum octet length when encoding BCD string to octet string,
     *                    or pass <code>-1</code> to perform minimum number of octets
     */
    public static void bcdString(OctetOutputStream stream, String bcd, int octetLength) {
        int[] digits = new int[bcd.length() + bcd.length() % 2];
        if (bcd.length() % 2 != 0)
            digits[digits.length - 1] = 0xF;

        for (int i = 0; i < bcd.length(); i++) {
            char c = bcd.charAt(i);
            if (c < '0' || c > '9')
                throw new IllegalArgumentException("BCD string contains invalid characters");
            digits[i] = c - '0';
        }

        int requiredOctets = (int) Math.ceil(bcd.length() / 2.0);
        if (octetLength == -1)
            octetLength = requiredOctets;
        if (requiredOctets > octetLength)
            throw new IllegalArgumentException("octetLength should be greater or equal to " + requiredOctets);

        for (int i = 0; i < digits.length / 2; i++) {
            int octet = digits[2 * i + 1] << 4 | digits[2 * i];
            stream.writeOctet(octet);
        }

        for (int i = digits.length / 2; i < octetLength; i++) {
            stream.writeOctet(0xFF);
        }
    }
}
