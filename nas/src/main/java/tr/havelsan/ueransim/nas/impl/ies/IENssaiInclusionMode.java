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

public class IENssaiInclusionMode extends InformationElement1 {
    public ENssaiInclusionMode nssaiInclusionMode;

    public IENssaiInclusionMode() {
    }

    public IENssaiInclusionMode(ENssaiInclusionMode nssaiInclusionMode) {
        this.nssaiInclusionMode = nssaiInclusionMode;
    }

    @Override
    public IENssaiInclusionMode decodeIE1(Bit4 value) {
        var res = new IENssaiInclusionMode();
        res.nssaiInclusionMode = ENssaiInclusionMode.fromValue(value.intValue() & 0b11);
        return res;
    }

    @Override
    public int encodeIE1() {
        return nssaiInclusionMode.intValue();
    }

    public static class ENssaiInclusionMode extends ProtocolEnum {
        public static final ENssaiInclusionMode A
                = new ENssaiInclusionMode(0b00, "NSSAI inclusion mode A");
        public static final ENssaiInclusionMode B
                = new ENssaiInclusionMode(0b01, "NSSAI inclusion mode B");
        public static final ENssaiInclusionMode C
                = new ENssaiInclusionMode(0b10, "NSSAI inclusion mode C");
        public static final ENssaiInclusionMode D
                = new ENssaiInclusionMode(0b11, "NSSAI inclusion mode D");


        private ENssaiInclusionMode(int value, String name) {
            super(value, name);
        }

        public static ENssaiInclusionMode fromValue(int value) {
            return fromValueGeneric(ENssaiInclusionMode.class, value, null);
        }
    }
}
