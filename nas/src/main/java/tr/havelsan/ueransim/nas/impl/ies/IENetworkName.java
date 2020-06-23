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

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IENetworkName extends InformationElement4 {
    public Bit3 numOfSpareBits;
    public EAddCountryInitials addCi;
    public ECodingScheme codingScheme;
    public OctetString textString;

    public IENetworkName() {
    }

    public IENetworkName(Bit3 numOfSpareBits, EAddCountryInitials addCi, ECodingScheme codingScheme, OctetString textString) {
        this.numOfSpareBits = numOfSpareBits;
        this.addCi = addCi;
        this.codingScheme = codingScheme;
        this.textString = textString;
    }

    @Override
    protected IENetworkName decodeIE4(OctetInputStream stream, int length) {
        int flags = stream.readOctetI();

        var res = new IENetworkName();
        res.numOfSpareBits = new Bit3(flags);
        res.addCi = EAddCountryInitials.fromValue(flags >> 3 & 0b1);
        res.codingScheme = ECodingScheme.fromValue(flags >> 4 & 0b111);
        res.textString = stream.readOctetString(length - 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var flags = new Octet();
        flags = flags.setBitRange(0, 2, numOfSpareBits.intValue());
        flags = flags.setBit(3, addCi.intValue());
        flags = flags.setBitRange(4, 6, codingScheme.intValue());
        stream.writeOctet(flags);
        stream.writeOctetString(textString);
    }

    public static class EAddCountryInitials extends ProtocolEnum {
        public static final EAddCountryInitials SHOULD_NOT_ADD
                = new EAddCountryInitials(0b0, "The MS should not add the letters for the Country's Initials to the text string");
        public static final EAddCountryInitials SHOULD_ADD
                = new EAddCountryInitials(0b1, "The MS should add the letters for the Country's Initials and a separator (e.g. a space) to the text string");

        private EAddCountryInitials(int value, String name) {
            super(value, name);
        }

        public static EAddCountryInitials fromValue(int value) {
            return fromValueGeneric(EAddCountryInitials.class, value, null);
        }
    }

    public static class ECodingScheme extends ProtocolEnum {
        public static final ECodingScheme DEFAULT
                = new ECodingScheme(0b000, "Cell Broadcast data coding scheme, GSM default alphabet, language unspecified, defined in 3GPP TS 23.038");
        public static final ECodingScheme UCS2
                = new ECodingScheme(0b001, "UCS2 (16 bit)");

        private ECodingScheme(int value, String name) {
            super(value, name);
        }

        public static ECodingScheme fromValue(int value) {
            return fromValueGeneric(ECodingScheme.class, value, null);
        }
    }
}
