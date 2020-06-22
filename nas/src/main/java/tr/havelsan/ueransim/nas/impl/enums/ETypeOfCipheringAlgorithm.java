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

public class ETypeOfCipheringAlgorithm extends ProtocolEnum {
    public static final ETypeOfCipheringAlgorithm EA0
            = new ETypeOfCipheringAlgorithm(0b0000, "5G encryption algorithm 5G-EA0 (null ciphering protection algorithm)");
    public static final ETypeOfCipheringAlgorithm EA1_128
            = new ETypeOfCipheringAlgorithm(0b0001, "5G encryption algorithm 128-5G-EA1");
    public static final ETypeOfCipheringAlgorithm EA2_128
            = new ETypeOfCipheringAlgorithm(0b0010, "5G encryption algorithm 128-5G-EA2");
    public static final ETypeOfCipheringAlgorithm EA3_128
            = new ETypeOfCipheringAlgorithm(0b0011, "5G encryption algorithm 128-5G-EA3");
    public static final ETypeOfCipheringAlgorithm EA4
            = new ETypeOfCipheringAlgorithm(0b0100, "5G encryption algorithm 5G-EA4");
    public static final ETypeOfCipheringAlgorithm EA5
            = new ETypeOfCipheringAlgorithm(0b0101, "5G encryption algorithm 5G-EA5");
    public static final ETypeOfCipheringAlgorithm EA6
            = new ETypeOfCipheringAlgorithm(0b0110, "5G encryption algorithm 5G-EA6");
    public static final ETypeOfCipheringAlgorithm EA7
            = new ETypeOfCipheringAlgorithm(0b0111, "5G encryption algorithm 5G-EA7");

    private ETypeOfCipheringAlgorithm(int value, String name) {
        super(value, name);
    }

    public static ETypeOfCipheringAlgorithm fromValue(int value) {
        return fromValueGeneric(ETypeOfCipheringAlgorithm.class, value, null);
    }
}