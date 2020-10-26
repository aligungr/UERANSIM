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

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class UeKeys {
    public OctetString rand;
    public OctetString res;
    public OctetString resStar; // used in 5G-AKA

    public OctetString kAusf;
    public OctetString kSeaf;
    public OctetString kAmf;
    public OctetString kNasInt;
    public OctetString kNasEnc;

    public UeKeys deepCopy() {
        var keys = new UeKeys();
        keys.rand = this.rand;
        keys.res = this.res;
        keys.resStar = this.resStar;
        keys.kAusf = this.kAusf;
        keys.kSeaf = this.kSeaf;
        keys.kAmf = this.kAmf;
        keys.kNasInt = this.kNasInt;
        keys.kNasEnc = this.kNasEnc;
        return keys;
    }
}
