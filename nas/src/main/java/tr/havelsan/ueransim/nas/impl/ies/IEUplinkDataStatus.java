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

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit8;

public class IEUplinkDataStatus extends InformationElement4 {
    public final Bit psi00 = new Bit(0);
    public Bit psi01;
    public Bit psi02;
    public Bit psi03;
    public Bit psi04;
    public Bit psi05;
    public Bit psi06;
    public Bit psi07;
    public Bit psi08;
    public Bit psi09;
    public Bit psi10;
    public Bit psi11;
    public Bit psi12;
    public Bit psi13;
    public Bit psi14;
    public Bit psi15;

    public IEUplinkDataStatus() {
    }

    public IEUplinkDataStatus(Bit psi01, Bit psi02, Bit psi03, Bit psi04, Bit psi05, Bit psi06, Bit psi07, Bit psi08, Bit psi09, Bit psi10, Bit psi11, Bit psi12, Bit psi13, Bit psi14, Bit psi15) {
        this.psi01 = psi01;
        this.psi02 = psi02;
        this.psi03 = psi03;
        this.psi04 = psi04;
        this.psi05 = psi05;
        this.psi06 = psi06;
        this.psi07 = psi07;
        this.psi08 = psi08;
        this.psi09 = psi09;
        this.psi10 = psi10;
        this.psi11 = psi11;
        this.psi12 = psi12;
        this.psi13 = psi13;
        this.psi14 = psi14;
        this.psi15 = psi15;
    }

    @Override
    protected IEUplinkDataStatus decodeIE4(OctetInputStream stream, int length) {
        var octet1 = stream.readOctet();
        var octet2 = stream.readOctet();

        var res = new IEUplinkDataStatus();
        res.psi01 = new Bit(octet1.getBitI(1));
        res.psi02 = new Bit(octet1.getBitI(2));
        res.psi03 = new Bit(octet1.getBitI(3));
        res.psi04 = new Bit(octet1.getBitI(4));
        res.psi05 = new Bit(octet1.getBitI(5));
        res.psi06 = new Bit(octet1.getBitI(6));
        res.psi07 = new Bit(octet1.getBitI(7));
        res.psi08 = new Bit(octet2.getBitI(0));
        res.psi09 = new Bit(octet2.getBitI(1));
        res.psi10 = new Bit(octet2.getBitI(2));
        res.psi11 = new Bit(octet2.getBitI(3));
        res.psi12 = new Bit(octet2.getBitI(4));
        res.psi13 = new Bit(octet2.getBitI(5));
        res.psi14 = new Bit(octet2.getBitI(6));
        res.psi15 = new Bit(octet2.getBitI(7));
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var octet1 = new Bit8(psi00, psi01, psi02, psi03, psi04, psi05, psi06, psi07);
        var octet2 = new Bit8(psi08, psi09, psi10, psi11, psi12, psi13, psi14, psi15);

        stream.writeOctet(octet1);
        stream.writeOctet(octet2);
    }
}
