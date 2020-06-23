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
import tr.havelsan.ueransim.utils.bits.Bit5;

public class IEGprsTimer3 extends InformationElement4 {
    public Bit5 timerValue;
    public EGprsTimerValueUnit3 unit;

    public IEGprsTimer3() {
    }

    public IEGprsTimer3(Bit5 timerValue, EGprsTimerValueUnit3 unit) {
        this.timerValue = timerValue;
        this.unit = unit;
    }

    @Override
    protected IEGprsTimer3 decodeIE4(OctetInputStream stream, int length) {
        var octet = stream.readOctet();

        var res = new IEGprsTimer3();
        res.timerValue = new Bit5(octet.getBitRangeI(0, 4));
        res.unit = EGprsTimerValueUnit3.fromValue(octet.getBitRangeI(5, 7));
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        int octet = unit.intValue() & 0b111;
        octet <<= 5;
        octet |= timerValue.intValue() & 0b11111;

        stream.writeOctet(octet);
    }

    public boolean hasValue() {
        return !unit.equals(EGprsTimerValueUnit3.DEACTIVATED) && timerValue.intValue() != 0;
    }

    public static class EGprsTimerValueUnit3 extends ProtocolEnum {
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_10MIN
                = new EGprsTimerValueUnit3(0b000, "value is incremented in multiples of 10 minutes");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_1HOUR
                = new EGprsTimerValueUnit3(0b001, "value is incremented in multiples of 1 hour");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_10HOUR
                = new EGprsTimerValueUnit3(0b010, "value is incremented in multiples of 10 hours");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_2SEC
                = new EGprsTimerValueUnit3(0b011, "value is incremented in multiples of 2 seconds");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_30HOUR
                = new EGprsTimerValueUnit3(0b100, "value is incremented in multiples of 30 seconds");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_1MIN
                = new EGprsTimerValueUnit3(0b101, "value is incremented in multiples of 1 minute");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_320HOUR
                = new EGprsTimerValueUnit3(0b110, "value is incremented in multiples of 320 hours");
        public static final EGprsTimerValueUnit3 DEACTIVATED
                = new EGprsTimerValueUnit3(0b111, "value indicates that the timer is deactivated");

        private EGprsTimerValueUnit3(int value, String name) {
            super(value, name);
        }

        public static EGprsTimerValueUnit3 fromValue(int value) {
            return fromValueGeneric(EGprsTimerValueUnit3.class, value, null);
        }
    }
}
