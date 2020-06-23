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
import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.octets.Octet;

public class IEGprsTimer extends InformationElement3 {
    public Bit5 timerValue;
    public EGprsTimerValueUnit timerValueUnit;

    public IEGprsTimer() {
    }

    public IEGprsTimer(Bit5 timerValue, EGprsTimerValueUnit timerValueUnit) {
        this.timerValue = timerValue;
        this.timerValueUnit = timerValueUnit;
    }

    @Override
    protected IEGprsTimer decodeIE3(OctetInputStream stream) {
        var res = new IEGprsTimer();
        var octet = stream.readOctet();
        res.timerValue = new Bit5(octet.getBitRangeI(0, 4));
        res.timerValueUnit = EGprsTimerValueUnit.fromValue(octet.getBitRangeI(5, 7));
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        var octet = new Octet();
        octet = octet.setBitRange(0, 4, timerValue.intValue());
        octet = octet.setBitRange(5, 5, timerValueUnit.intValue());
        stream.writeOctet(octet);
    }

    public static class EGprsTimerValueUnit extends ProtocolEnum {
        public static final EGprsTimerValueUnit MULTIPLES_OF_2_SECONDS
                = new EGprsTimerValueUnit(0b000, "value is incremented in multiples of 2 seconds");
        public static final EGprsTimerValueUnit MULTIPLES_OF_1_MINUTE
                = new EGprsTimerValueUnit(0b001, "value is incremented in multiples of 1 minute ");
        public static final EGprsTimerValueUnit MULTIPLES_OF_DECIHOURS
                = new EGprsTimerValueUnit(0b010, "value is incremented in multiples of decihours");
        public static final EGprsTimerValueUnit TIMER_IS_DEACTIVATED
                = new EGprsTimerValueUnit(0b111, "value indicates that the timer is deactivated.");

        private EGprsTimerValueUnit(int value, String name) {
            super(value, name);
        }

        public static EGprsTimerValueUnit fromValue(int value) {
            return fromValueGeneric(EGprsTimerValueUnit.class, value, null);
        }
    }
}
