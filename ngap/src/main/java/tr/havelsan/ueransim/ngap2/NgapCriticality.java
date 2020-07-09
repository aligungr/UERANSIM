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

package tr.havelsan.ueransim.ngap2;

public enum NgapCriticality {
    REJECT,
    IGNORE,
    NOTIFY;

    public static NgapCriticality fromAsnValue(int value) {
        switch (value) {
            case 0:
                return REJECT;
            case 1:
                return IGNORE;
            case 2:
                return NOTIFY;
            default:
                throw new RuntimeException("NgapCriticality.fromAsnValue invalid value: " + value);
        }
    }

    public static NgapCriticality fromAsnValue(String value) {
        switch (value) {
            case "REJECT":
            case "reject":
                return REJECT;
            case "IGNORE":
            case "ignore":
                return IGNORE;
            case "NOTIFY":
            case "notify":
                return NOTIFY;
            default:
                throw new RuntimeException("NgapCriticality.fromAsnValue invalid value: " + value);
        }
    }

    int getAsnValue() {
        switch (this) {
            case REJECT:
                return 0;
            case IGNORE:
                return 1;
            case NOTIFY:
                return 2;
            default:
                throw new RuntimeException();
        }
    }
}
