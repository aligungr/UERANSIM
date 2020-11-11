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
 */

package tr.havelsan.ueransim.ngap0;

public class NgapJni {

    private static final int ATS_NONSTANDARD_PLAINTEXT = 1;
    private static final int ATS_UNALIGNED_BASIC_PER = 8;
    private static final int ATS_UNALIGNED_CANONICAL_PER = 9;
    private static final int ATS_ALIGNED_BASIC_PER = 10;
    private static final int ATS_ALIGNED_CANONICAL_PER = 11;
    private static final int ATS_BASIC_XER = 12;
    private static final int ATS_CANONICAL_XER = 13;

    private static final int RESULT_OK = 0;
    private static final int RESULT_DECODING_FAILED = 1;
    private static final int RESULT_ENCODING_FAILED = 2;

    private static native byte[] convertEncoding(byte[] data, int fromEncoding, int toEncoding, int[] result, int pduType);

    private static byte[] convert(byte[] data, int fromEncoding, int toEncoding, NgapDataUnitType pduType) {
        int[] resCode = new int[1];
        var converted = convertEncoding(data, fromEncoding, toEncoding, resCode, pduType.value);
        if (resCode[0] == RESULT_DECODING_FAILED) {
            throw new RuntimeException("NgapJni convertEncoding RESULT_DECODING_FAILED");
        }
        if (resCode[0] == RESULT_ENCODING_FAILED) {
            throw new RuntimeException("NgapJni convertEncoding RESULT_ENCODING_FAILED");
        }
        return converted;
    }

    public static String aperToXer(byte[] data, NgapDataUnitType pduType) {
        var converted = convert(data, ATS_ALIGNED_CANONICAL_PER, ATS_CANONICAL_XER, pduType);
        return new String(converted);
    }

    public static String aperToPlainText(byte[] data, NgapDataUnitType pduType) {
        var converted = convert(data, ATS_ALIGNED_CANONICAL_PER, ATS_NONSTANDARD_PLAINTEXT, pduType);
        return new String(converted);
    }

    public static byte[] xerToAper(String xer, NgapDataUnitType pduType) {
        return convert(xer.getBytes(), ATS_CANONICAL_XER, ATS_ALIGNED_CANONICAL_PER, pduType);
    }
}
