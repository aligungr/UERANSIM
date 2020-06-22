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

public class IEAccessType extends InformationElement1 {
    public EAccessType accessType;

    public IEAccessType() {
    }

    public IEAccessType(EAccessType accessType) {
        this.accessType = accessType;
    }

    @Override
    public IEAccessType decodeIE1(Bit4 value) {
        var res = new IEAccessType();
        res.accessType = EAccessType.fromValue(value.intValue() & 0b11);
        return res;
    }

    @Override
    public int encodeIE1() {
        return accessType.intValue();
    }

    public static class EAccessType extends ProtocolEnum {
        public static final EAccessType THREEGPP_ACCESS
                = new EAccessType(0b01, "3GPP access");
        public static final EAccessType NON_THREEGPP_ACCESS
                = new EAccessType(0b10, "Non-3GPP access");

        private EAccessType(int value, String name) {
            super(value, name);
        }

        public static EAccessType fromValue(int value) {
            return fromValueGeneric(EAccessType.class, value, null);
        }
    }
}
