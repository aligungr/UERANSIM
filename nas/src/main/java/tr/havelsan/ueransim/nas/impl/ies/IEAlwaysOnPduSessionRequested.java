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

public class IEAlwaysOnPduSessionRequested extends InformationElement1 {
    public EAlwaysOnPduSessionRequested aprs;

    public IEAlwaysOnPduSessionRequested() {
    }

    public IEAlwaysOnPduSessionRequested(EAlwaysOnPduSessionRequested aprs) {
        this.aprs = aprs;
    }

    @Override
    public InformationElement1 decodeIE1(Bit4 value) {
        var res = new IEAlwaysOnPduSessionRequested();
        res.aprs = EAlwaysOnPduSessionRequested.fromValue(value.getBitI(0));
        return res;
    }

    @Override
    public int encodeIE1() {
        return aprs.intValue();
    }

    public static class EAlwaysOnPduSessionRequested extends ProtocolEnum {
        public static final EAlwaysOnPduSessionRequested NOT_REQUESTED
                = new EAlwaysOnPduSessionRequested(0b0, "Always-on PDU session not requested");
        public static final EAlwaysOnPduSessionRequested REQUESTED
                = new EAlwaysOnPduSessionRequested(0b1, "Always-on PDU session requested");

        private EAlwaysOnPduSessionRequested(int value, String name) {
            super(value, name);
        }

        public static EAlwaysOnPduSessionRequested fromValue(int value) {
            return fromValueGeneric(EAlwaysOnPduSessionRequested.class, value, null);
        }
    }
}
