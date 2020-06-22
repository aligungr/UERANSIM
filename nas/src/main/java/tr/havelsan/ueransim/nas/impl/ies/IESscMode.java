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
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IESscMode extends InformationElement1 {
    public ESscMode sscMode;

    public IESscMode() {
    }

    public IESscMode(ESscMode sscMode) {
        this.sscMode = sscMode;
    }

    @Override
    public IESscMode decodeIE1(Bit4 value) {
        var res = new IESscMode();
        res.sscMode = ESscMode.fromValue(value.intValue() & 0b111);
        return res;
    }

    @Override
    public int encodeIE1() {
        return sscMode.intValue();
    }

    public static class ESscMode extends ProtocolEnum {
        public static final ESscMode SSC_MODE_1
                = new ESscMode(0b001, "SSC mode 1");
        public static final ESscMode SSC_MODE_2
                = new ESscMode(0b010, "SSC mode 2");
        public static final ESscMode SSC_MODE_3
                = new ESscMode(0b011, "SSC mode 3");
        public static final ESscMode UNUSED_1
                = new ESscMode(0b100, "unused; shall be interpreted as \"SSC mode 1\", if received by the network");
        public static final ESscMode UNUSED_2
                = new ESscMode(0b101, "unused; shall be interpreted as \"SSC mode 2\", if received by the network");
        public static final ESscMode UNUSED_3
                = new ESscMode(0b110, "unused; shall be interpreted as \"SSC mode 3\", if received by the network");

        private ESscMode(int value, String name) {
            super(value, name);
        }

        public static ESscMode fromValue(int value) {
            var val = fromValueGeneric(ESscMode.class, value, null);
            if (val.equals(UNUSED_1)) return SSC_MODE_1;
            if (val.equals(UNUSED_2)) return SSC_MODE_2;
            if (val.equals(UNUSED_3)) return SSC_MODE_3;
            return val;
        }
    }
}
