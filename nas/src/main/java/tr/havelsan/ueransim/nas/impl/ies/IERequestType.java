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

public class IERequestType extends InformationElement1 {
    public ERequestType requestType;

    public IERequestType() {
    }

    public IERequestType(ERequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public IERequestType decodeIE1(Bit4 value) {
        var res = new IERequestType();
        res.requestType = ERequestType.fromValue(value.intValue() & 0b111);
        return res;
    }

    @Override
    public int encodeIE1() {
        return requestType.intValue();
    }

    public static class ERequestType extends ProtocolEnum {
        public static final ERequestType INITIAL_REQUEST
                = new ERequestType(0b001, "initial request");
        public static final ERequestType EXISTING_PDU_SESSION
                = new ERequestType(0b010, "existing PDU session");
        public static final ERequestType INITIAL_EMERGENCY_REQUEST
                = new ERequestType(0b011, "initial emergency request");
        public static final ERequestType EXISTING_EMERGENCY_PDU_SESSION
                = new ERequestType(0b100, "existing emergency PDU session");
        public static final ERequestType MODIFICATION_REQUEST
                = new ERequestType(0b101, "modification request");
        //public static final ERequestType RESERVED
        //        = new ERequestType(0b111, "reserved");

        private ERequestType(int value, String name) {
            super(value, name);
        }

        public static ERequestType fromValue(int value) {
            return fromValueGeneric(ERequestType.class, value, null);
        }
    }
}
