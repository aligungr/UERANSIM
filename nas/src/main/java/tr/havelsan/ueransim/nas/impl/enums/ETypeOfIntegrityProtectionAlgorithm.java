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

public class ETypeOfIntegrityProtectionAlgorithm extends ProtocolEnum {
    public static final ETypeOfIntegrityProtectionAlgorithm IA0
            = new ETypeOfIntegrityProtectionAlgorithm(0b0000, "5G integrity algorithm 5G-IA0 (null integrity protection algorithm)");
    public static final ETypeOfIntegrityProtectionAlgorithm IA1_128
            = new ETypeOfIntegrityProtectionAlgorithm(0b0001, "5G integrity algorithm 128-5G-IA1");
    public static final ETypeOfIntegrityProtectionAlgorithm IA2_128
            = new ETypeOfIntegrityProtectionAlgorithm(0b0010, "5G integrity algorithm 128-5G-IA2");
    public static final ETypeOfIntegrityProtectionAlgorithm IA3_128
            = new ETypeOfIntegrityProtectionAlgorithm(0b0011, "5G integrity algorithm 128-5G-IA3");
    public static final ETypeOfIntegrityProtectionAlgorithm IA4
            = new ETypeOfIntegrityProtectionAlgorithm(0b0100, "5G integrity algorithm 5G-IA4");
    public static final ETypeOfIntegrityProtectionAlgorithm IA5
            = new ETypeOfIntegrityProtectionAlgorithm(0b0101, "5G integrity algorithm 5G-IA5");
    public static final ETypeOfIntegrityProtectionAlgorithm IA6
            = new ETypeOfIntegrityProtectionAlgorithm(0b0110, "5G integrity algorithm 5G-IA6");
    public static final ETypeOfIntegrityProtectionAlgorithm IA7
            = new ETypeOfIntegrityProtectionAlgorithm(0b0111, "5G integrity algorithm 5G-IA7");

    private ETypeOfIntegrityProtectionAlgorithm(int value, String name) {
        super(value, name);
    }

    public static ETypeOfIntegrityProtectionAlgorithm fromValue(int value) {
        return fromValueGeneric(ETypeOfIntegrityProtectionAlgorithm.class, value, null);
    }
}