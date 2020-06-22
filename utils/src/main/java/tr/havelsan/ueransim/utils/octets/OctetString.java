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

package tr.havelsan.ueransim.utils.octets;

import tr.havelsan.ueransim.utils.Utils;

import java.util.Arrays;
import java.util.Iterator;

public final class OctetString implements Iterable<Octet> {
    public final int length;
    private final Octet[] data;

    public OctetString(Octet... octets) {
        this.data = octets;
        this.length = octets.length;
    }

    public OctetString(int[] octetInts) {
        var data = new Octet[octetInts.length];
        for (int i = 0; i < octetInts.length; i++)
            data[i] = new Octet(octetInts[i]);

        this.data = data;
        this.length = data.length;
    }

    public OctetString(byte[] octetBytes) {
        var data = new Octet[octetBytes.length];
        for (int i = 0; i < octetBytes.length; i++)
            data[i] = new Octet(octetBytes[i] & 0xFF);

        this.data = data;
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

        Octet[] arr = new Octet[totalLength];
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
        Octet[] arr = s1.getAsArray();
        for (int i = 0; i < s1.length; i++) {
            arr[i] = new Octet(arr[i].intValue() ^ s2.get(i).intValue());
        }
        return new OctetString(arr);
    }

    public Octet get(int index) {
        return data[index];
    }

    public Octet2 get2(int index) {
        return new Octet2(get(index), get(index + 1));
    }

    public Octet3 get3(int index) {
        return new Octet3(get(index), get(index + 1), get(index + 2));
    }

    public Octet4 get4(int index) {
        return new Octet4(get(index), get(index + 1), get(index + 2), get(index + 3));
    }

    public Octet[] getAsArray() {
        var res = new Octet[length];
        System.arraycopy(data, 0, res, 0, length);
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
        forEach(octet -> {
            sb.append(String.format("%02x", octet.intValue()));
            if (withSpace) sb.append(' ');
        });
        return sb.toString().trim();
    }

    @Override
    public Iterator<Octet> iterator() {
        return Arrays.stream(data).iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OctetString))
            return false;
        var os = (OctetString) obj;
        if (os.length != this.length)
            return false;
        for (int i = 0; i < os.length; i++) {
            if (os.data[i].intValue() != this.data[i].intValue())
                return false;
        }
        return true;
    }

    public byte[] toByteArray() {
        var octetArray = getAsArray();
        var byteArray = new byte[octetArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) (octetArray[i].longValue() & 0xFF);
        }
        return byteArray;
    }

    public OctetString substring(int startIndex) {
        var data = new Octet[this.length - startIndex];
        System.arraycopy(this.data, startIndex, data, 0, data.length);
        return new OctetString(data);
    }

    public OctetString substring(int startIndex, int length) {
        var data = new Octet[length];
        System.arraycopy(this.data, startIndex, data, 0, data.length);
        return new OctetString(data);
    }
}
