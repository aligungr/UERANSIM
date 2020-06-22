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

public class ESecurityHeaderType extends ProtocolEnum {
    public static final ESecurityHeaderType NOT_PROTECTED
            = new ESecurityHeaderType(0b0000, "Plain 5GS NAS message, not security protected");
    public static final ESecurityHeaderType INTEGRITY_PROTECTED
            = new ESecurityHeaderType(0b0001, "Integrity protected");
    public static final ESecurityHeaderType INTEGRITY_PROTECTED_AND_CIPHERED
            = new ESecurityHeaderType(0b0010, "Integrity protected and ciphered");
    public static final ESecurityHeaderType INTEGRITY_PROTECTED_WITH_NEW_SECURITY_CONTEXT
            = new ESecurityHeaderType(0b0011, "Integrity protected with new 5G NAS security context");
    public static final ESecurityHeaderType INTEGRITY_PROTECTED_AND_CIPHERED_WITH_NEW_SECURITY_CONTEXT
            = new ESecurityHeaderType(0b0100, "Integrity protected and ciphered with new 5G NAS security context");

    private ESecurityHeaderType(int value, String name) {
        super(value, name);
    }

    public static ESecurityHeaderType fromValue(int value) {
        return fromValueGeneric(ESecurityHeaderType.class, value, null);
    }

    public boolean isIntegrityProtected() {
        return this.equals(INTEGRITY_PROTECTED) || this.equals(INTEGRITY_PROTECTED_AND_CIPHERED)
                || this.equals(INTEGRITY_PROTECTED_WITH_NEW_SECURITY_CONTEXT) || this.equals(INTEGRITY_PROTECTED_AND_CIPHERED_WITH_NEW_SECURITY_CONTEXT);
    }

    public boolean isCiphered() {
        return this.equals(INTEGRITY_PROTECTED_AND_CIPHERED) || this.equals(INTEGRITY_PROTECTED_AND_CIPHERED_WITH_NEW_SECURITY_CONTEXT);
    }
}
