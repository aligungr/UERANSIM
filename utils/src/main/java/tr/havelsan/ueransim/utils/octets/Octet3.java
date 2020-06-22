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
import tr.havelsan.ueransim.utils.bits.Bit;

/**
 * Represents 3-octet or 24-bit unsigned integer
 */
public final class Octet3 extends OctetN {

    public Octet3() {
        this(0);
    }

    public Octet3(long value) {
        super(value, 3);
    }

    public Octet3(int big, int middle, int little) {
        this(((big & 0xFF) << 16) | ((middle & 0xFF) << 8) | (little & 0xFF));
    }

    public Octet3(Octet big, Octet middle, Octet little) {
        this(big.intValue(), middle.intValue(), little.intValue());
    }

    public Octet3(String hex) {
        this(Utils.toLong(hex));
    }

    @Override
    public final Octet3 setBit(int index, int bit) {
        return new Octet3(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet3 setBit(int index, Bit bit) {
        return new Octet3(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet3 setBitRange(int start, int end, long value) {
        return new Octet3(super.setBitRange(start, end, value).longValue());
    }
}
