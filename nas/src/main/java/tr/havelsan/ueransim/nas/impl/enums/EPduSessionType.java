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

public class EPduSessionType extends ProtocolEnum {
    public static final EPduSessionType IPV4
            = new EPduSessionType(0b001, "IPv4");
    public static final EPduSessionType IPV6
            = new EPduSessionType(0b010, "IPv6");
    public static final EPduSessionType IPV4V6
            = new EPduSessionType(0b011, "IPv4v6");
    public static final EPduSessionType UNSTRUCTURED
            = new EPduSessionType(0b100, "Unstructured");
    public static final EPduSessionType ETHERNET
            = new EPduSessionType(0b101, "Ethernet");
    //public static final EPduSessionType RESERVED
    //        = new EPduSessionType(0b111, "reserved");

    private EPduSessionType(int value, String name) {
        super(value, name);
    }

    public static EPduSessionType fromValue(int value) {
        return fromValueGeneric(EPduSessionType.class, value, null);
    }
}
