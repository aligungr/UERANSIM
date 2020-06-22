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

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EPduSessionIdentity extends ProtocolEnum {
    public static final EPduSessionIdentity NO_VAL
            = new EPduSessionIdentity(0b00000000, "No PDU session identity assigned");
    public static final EPduSessionIdentity VAL_1
            = new EPduSessionIdentity(0b00000001, "PDU session identity value 1");
    public static final EPduSessionIdentity VAL_2
            = new EPduSessionIdentity(0b00000010, "PDU session identity value 2");
    public static final EPduSessionIdentity VAL_3
            = new EPduSessionIdentity(0b00000011, "PDU session identity value 3");
    public static final EPduSessionIdentity VAL_4
            = new EPduSessionIdentity(0b00000100, "PDU session identity value 4");
    public static final EPduSessionIdentity VAL_5
            = new EPduSessionIdentity(0b00000101, "PDU session identity value 5");
    public static final EPduSessionIdentity VAL_6
            = new EPduSessionIdentity(0b00000110, "PDU session identity value 6");
    public static final EPduSessionIdentity VAL_7
            = new EPduSessionIdentity(0b00000111, "PDU session identity value 7");
    public static final EPduSessionIdentity VAL_8
            = new EPduSessionIdentity(0b00001000, "PDU session identity value 8");
    public static final EPduSessionIdentity VAL_9
            = new EPduSessionIdentity(0b00001001, "PDU session identity value 9");
    public static final EPduSessionIdentity VAL_10
            = new EPduSessionIdentity(0b00001010, "PDU session identity value 10");
    public static final EPduSessionIdentity VAL_11
            = new EPduSessionIdentity(0b00001011, "PDU session identity value 11");
    public static final EPduSessionIdentity VAL_12
            = new EPduSessionIdentity(0b00001100, "PDU session identity value 12");
    public static final EPduSessionIdentity VAL_13
            = new EPduSessionIdentity(0b00001101, "PDU session identity value 13");
    public static final EPduSessionIdentity VAL_14
            = new EPduSessionIdentity(0b00001110, "PDU session identity value 14");
    public static final EPduSessionIdentity VAL_15
            = new EPduSessionIdentity(0b00001111, "PDU session identity value 15");

    private EPduSessionIdentity(int value, String name) {
        super(value, name);
    }

    public static EPduSessionIdentity fromValue(int value) {
        return fromValueGeneric(EPduSessionIdentity.class, value, null);
    }
}
