package com.runsim.backend.nas;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit4;
import com.runsim.backend.utils.octets.Octet;

public class Encoder {

    /**
     * Encodes NAS/5GS PDU into byte array
     */
    public static byte[] nasPdu(NasMessage pdu) {
        return new NasEncoder().encodeNAS(pdu);
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
        int bigHalf = big.intValue;
        int littleHalf = little.encodeIE1() & 0xF;
        stream.writeOctet(bigHalf, littleHalf);
    }

    /**
     * Encodes information element type 1
     */
    public static void ie1(OctetOutputStream stream, InformationElement1 big, Bit4 little) {
        int bigHalf = big.encodeIE1() & 0xF;
        int littleHalf = little.intValue;
        stream.writeOctet(bigHalf, littleHalf);
    }

    /**
     * Encodes information element type 6 without IEI. 2-octet length is automatically inserted at the start
     * as type 6 elements requires length indicator.
     */
    public static void ie6(OctetOutputStream stream, InformationElement6 ie) {
        var newStream = new OctetOutputStream();
        ie.encodeIE6(newStream);
        stream.writeOctet2(newStream.length());
        stream.writeStream(newStream);
    }

    /**
     * Encodes information element type 6 with IEI
     */
    public static void ie6(OctetOutputStream stream, Octet iei, InformationElement6 ie) {
        stream.writeOctet(iei);
        ie6(stream, ie);
    }

    /**
     * Encodes information element type 6 with IEI
     */
    public static void ie6(OctetOutputStream stream, int iei, InformationElement6 ie) {
        ie6(stream, new Octet(iei), ie);
    }
}
