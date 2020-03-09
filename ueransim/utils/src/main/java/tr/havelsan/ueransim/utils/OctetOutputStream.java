package tr.havelsan.ueransim.utils;

import tr.havelsan.ueransim.utils.bits.Bit4;
import tr.havelsan.ueransim.utils.bits.Bit8;
import tr.havelsan.ueransim.utils.bits.BitN;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.utils.octets.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OctetOutputStream {
    private final List<Octet> data;
    private final boolean isBigEndian;

    public OctetOutputStream() {
        this(true);
    }

    public OctetOutputStream(boolean isBigEndian) {
        this.data = new ArrayList<>();
        this.isBigEndian = isBigEndian;
    }

    public void writeOctet(int octet) {
        writeOctet(new Octet(octet));
    }

    public void writeOctet(Octet octet) {
        data.add(octet);
    }

    public void writeOctet(Bit8 octet) {
        writeOctet(octet.intValue());
    }

    public void writeOctet(Bit4 bigHalf, Bit4 littleHalf) {
        writeOctet(bigHalf.intValue(), littleHalf.intValue());
    }

    public void writeOctet(int bigHalf, int littleHalf) {
        bigHalf &= 0xF;
        littleHalf &= 0xF;
        writeOctet(bigHalf << 4 | littleHalf);
    }

    public void writeOctet2(Octet bigOctet, Octet littleOctet) {
        if (isBigEndian) {
            writeOctet(bigOctet);
            writeOctet(littleOctet);
        } else {
            writeOctet(littleOctet);
            writeOctet(bigOctet);
        }
    }

    public void writeOctet2(int bigOctet, int littleOctet) {
        if (isBigEndian) {
            writeOctet(bigOctet);
            writeOctet(littleOctet);
        } else {
            writeOctet(littleOctet);
            writeOctet(bigOctet);
        }
    }

    public void writeOctet2(Octet2 octet2) {
        if (isBigEndian) {
            writeOctet(octet2.intValue() >> 8 & 0xFF);
            writeOctet(octet2.intValue() & 0xFF);
        } else {
            writeOctet(octet2.intValue() & 0xFF);
            writeOctet(octet2.intValue() >> 8 & 0xFF);
        }
    }

    public void writeOctet2(int octet2) {
        writeOctet2(new Octet2(octet2));
    }

    public void writeOctet4(Octet4 octet4) {
        writeOctetN(octet4);
    }

    public void writeOctet4(long octet4) {
        writeOctet4(new Octet4(octet4));
    }

    public void writeOctet3(Octet3 octet3) {
        writeOctetN(octet3);
    }

    public void writeOctet3(int octet3) {
        writeOctet3(new Octet3(octet3));
    }

    public void writeStream(OctetOutputStream stream) {
        var data = stream.data;
        for (var octet : data) {
            writeOctet(octet);
        }
    }

    public void writeOctetString(OctetString octetString) {
        writeOctets(octetString.data);
    }

    public void writeOctets(Octet[] octets) {
        data.addAll(Arrays.asList(octets));
    }

    private void writeOctetN(OctetN octets) {
        writeOctets(octets.toOctetArray(isBigEndian));
    }

    public void writeOctets(int[] octets) {
        for (int octet : octets) {
            writeOctet(octet & 0xFF);
        }
    }

    public void writeOctets(byte[] octets) {
        for (byte octet : octets) {
            writeOctet(octet & 0xFF);
        }
    }

    public void writeBits(BitN value, boolean useMsb) {
        writeOctets(value.toOctetArray(isBigEndian, useMsb));
    }

    public void writeBits(BitN value) {
        writeBits(value, true);
    }

    public Octet[] toOctetArray() {
        return data.toArray(new Octet[0]);
    }

    public int[] toIntArray() {
        Octet[] arr = toOctetArray();
        int[] buf = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            buf[i] = arr[i].intValue();
        return buf;
    }

    public byte[] toByteArray() {
        Octet[] arr = toOctetArray();
        byte[] buf = new byte[arr.length];
        for (int i = 0; i < arr.length; i++)
            buf[i] = (byte) arr[i].intValue();
        return buf;
    }

    public int length() {
        return data.size();
    }
}
