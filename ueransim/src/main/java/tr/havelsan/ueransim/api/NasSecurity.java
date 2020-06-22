package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.core.NasSecurityContext;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.SecuredMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.nas.impl.messages.SecurityModeCommand;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

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
                Logging.warning(Tag.NAS_SECURITY, "Message type or Security Header Type is semantically incorrect. Ignoring received NAS message.");
                return null;
            }
        }

        if (securedMmMessage.securityHeaderType.equals(ESecurityHeaderType.INTEGRITY_PROTECTED_AND_CIPHERED_WITH_NEW_SECURITY_CONTEXT)) {
            Logging.warning(Tag.NAS_SECURITY, "Message type or Security Header Type is semantically incorrect. Ignoring received NAS message.");
            return null;
        }

        var decrypted = NasEncryption.decrypt(securedMmMessage, nsc);
        if (decrypted == null) {
            Logging.error(Tag.NAS_SECURITY, "MAC mismatch in NAS encryption. Ignoring received NAS Message.");
            return null;
        } else {
            return decrypted;
        }
    }
}