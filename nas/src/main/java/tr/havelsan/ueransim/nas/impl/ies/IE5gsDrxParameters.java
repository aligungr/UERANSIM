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

public class IE5gsDrxParameters extends InformationElement4 {
    public EDrxValue drxValue;

    public IE5gsDrxParameters() {
    }

    public IE5gsDrxParameters(EDrxValue drxValue) {
        this.drxValue = drxValue;
    }

    @Override
    protected IE5gsDrxParameters decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsDrxParameters();
        res.drxValue = EDrxValue.fromValue(stream.readOctetI() & 0xF);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(drxValue.intValue());
    }

    public static class EDrxValue extends ProtocolEnum {
        public static final EDrxValue NOT_SPECIFIED
                = new EDrxValue(0b0000, "DRX value not specified");
        public static final EDrxValue T32
                = new EDrxValue(0b0001, "DRX cycle parameter T = 32");
        public static final EDrxValue T64
                = new EDrxValue(0b0010, "DRX cycle parameter T = 64");
        public static final EDrxValue T128
                = new EDrxValue(0b0011, "DRX cycle parameter T = 128");
        public static final EDrxValue T256
                = new EDrxValue(0b0100, "DRX cycle parameter T = 256");

        private EDrxValue(int value, String name) {
            super(value, name);
        }

        public static EDrxValue fromValue(int value) {
            return fromValueGeneric(EDrxValue.class, value, null);
        }
    }
}
