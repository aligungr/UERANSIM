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

package tr.havelsan.ueransim.utils.octets;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;

/**
 * Represents 1-octet or 8-bit unsigned integer
 */
public final class Octet extends OctetN {

    public Octet() {
        this(0);
    }

    public Octet(byte value) {
        this(value & 0xFF);
    }

    public Octet(int value) {
        this((long) value);
    }

    public Octet(String hex) {
        this(Utils.toLong(hex));
    }

    public Octet(long value) {
        super(value, 1);
    }

    @Override
    public final Octet setBit(int index, int bit) {
        return new Octet(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet setBit(int index, Bit bit) {
        return new Octet(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet setBitRange(int start, int end, long value) {
        return new Octet(super.setBitRange(start, end, value).longValue());
    }
}
