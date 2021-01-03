/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils.octets;

import tr.havelsan.ueransim.utils.Utils;

public final class OctetString {
    public static final OctetString EMPTY = new OctetString();

    public final int length;
    private final byte[] data;

    public OctetString(Octet... octets) {
        var data = new byte[octets.length];
        for (int i = 0; i < octets.length; i++)
            data[i] = (byte) (octets[i].intValue() & 0xFF);

        this.data = data;
        this.length = data.length;
    }

    public OctetString(int[] octetInts) {
        var data = new byte[octetInts.length];
        for (int i = 0; i < octetInts.length; i++)
            data[i] = (byte) (octetInts[i] & 0xFF);

        this.data = data;
        this.length = data.length;
    }

    // TODO: Or just wrap the byte array
    public OctetString(byte[] octetBytes) {
        var sub = new byte[octetBytes.length];
        System.arraycopy(octetBytes, 0, sub, 0, octetBytes.length);

        this.data = sub;
        this.length = data.length;
    }

    // TODO: Or just wrap the byte array
    public OctetString(byte[] buffer, int offset, int length) {
        var sub = new byte[length];
        System.arraycopy(buffer, offset, sub, 0, length);

        this.data = sub;
        this.length = data.length;
    }

    public OctetString(String hex) {
        this(Utils.hexStringToByteArray(hex));
    }

    public static OctetString concat(OctetString... octetStrings) {
        int totalLength = 0;
        for (var octetString : octetStrings) {
            totalLength += octetString.length;
        }

        byte[] arr = new byte[totalLength];
        int index = 0;

        for (var octetString : octetStrings) {
            for (var octet : octetString.data) {
                arr[index++] = octet;
            }
        }
        return new OctetString(arr);
    }

    public static OctetString xor(OctetString s1, OctetString s2) {
        if (s1.length != s2.length) {
            throw new IllegalStateException("s1.length != s2.length");
        }
        Octet[] arr = s1.getAsOctetArray();
        for (int i = 0; i < s1.length; i++) {
            arr[i] = new Octet(arr[i].intValue() ^ s2.get1(i).intValue());
        }
        return new OctetString(arr);
    }

    public byte get(int index) {
        return data[index];
    }

    public Octet get1(int index) {
        return new Octet(data[index]);
    }

    public Octet2 get2(int index) {
        return new Octet2(get1(index), get1(index + 1));
    }

    public Octet3 get3(int index) {
        return new Octet3(get1(index), get1(index + 1), get1(index + 2));
    }

    public Octet4 get4(int index) {
        return new Octet4(get1(index), get1(index + 1), get1(index + 2), get1(index + 3));
    }

    public Octet[] getAsOctetArray() {
        var res = new Octet[length];
        for (int i = 0; i < length; i++) {
            res[i] = new Octet(data[i]);
        }
        return res;
    }

    @Override
    public String toString() {
        return toHexString(false);
    }

    public String toHexString() {
        return this.toHexString(false);
    }

    public String toHexString(boolean withSpace) {
        var sb = new StringBuilder();
        for (byte octet : data) {
            sb.append(String.format("%02x", octet));
            if (withSpace) sb.append(' ');
        }
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OctetString))
            return false;
        var os = (OctetString) obj;
        if (os.length != this.length)
            return false;
        for (int i = 0; i < os.length; i++) {
            if (os.data[i] != this.data[i])
                return false;
        }
        return true;
    }

    public byte[] toByteArray() {
        var octetArray = getAsOctetArray();
        var byteArray = new byte[octetArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) (octetArray[i].longValue() & 0xFF);
        }
        return byteArray;
    }

    public OctetString substring(int startIndex) {
        var data = new byte[this.length - startIndex];
        System.arraycopy(this.data, startIndex, data, 0, data.length);
        return new OctetString(data);
    }

    public OctetString substring(int startIndex, int length) {
        var data = new byte[length];
        System.arraycopy(this.data, startIndex, data, 0, data.length);
        return new OctetString(data);
    }
}
