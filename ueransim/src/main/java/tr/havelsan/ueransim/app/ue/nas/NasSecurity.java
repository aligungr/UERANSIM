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

package tr.havelsan.ueransim.app.ue.nas;

import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.SecuredMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.nas.impl.messages.SecurityModeCommand;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class NasSecurity {

    public static NasMessage encryptNasMessage(NasSecurityContext nsc, NasMessage nasMessage) {
        if (nasMessage == null) {
            return null;
        }

        if (nsc == null || nsc.keys.kNasEnc == null) {
            return nasMessage;
        }

        return NasEncryption.encrypt(nasMessage, nsc);
    }

    public static NasMessage decryptNasMessage(NasSecurityContext nsc, NasMessage nasMessage) {
        if (nasMessage == null) {
            return null;
        }
        if (!(nasMessage instanceof SecuredMmMessage)) {
            return nasMessage;
        }

        SecuredMmMessage securedMmMessage = (SecuredMmMessage) nasMessage;

        if (securedMmMessage.securityHeaderType.equals(ESecurityHeaderType.INTEGRITY_PROTECTED_WITH_NEW_SECURITY_CONTEXT)) {
            var plainMessage = NasDecoder.nasPdu(securedMmMessage.plainNasMessage);
            if (plainMessage instanceof SecurityModeCommand) {
                var smc = (SecurityModeCommand) plainMessage;
                smc._macForNewSC = securedMmMessage.messageAuthenticationCode;
                return smc;
            } else {
                Log.warning(Tag.NAS_SECURITY, "Message type or Security Header Type is semantically incorrect. Ignoring received NAS message.");
                return null;
            }
        }

        if (securedMmMessage.securityHeaderType.equals(ESecurityHeaderType.INTEGRITY_PROTECTED_AND_CIPHERED_WITH_NEW_SECURITY_CONTEXT)) {
            Log.warning(Tag.NAS_SECURITY, "Message type or Security Header Type is semantically incorrect. Ignoring received NAS message.");
            return null;
        }

        var decrypted = NasEncryption.decrypt(securedMmMessage, nsc);
        if (decrypted == null) {
            Log.error(Tag.NAS_SECURITY, "MAC mismatch in NAS encryption. Ignoring received NAS Message.");
            return null;
        } else {
            return decrypted;
        }
    }
}