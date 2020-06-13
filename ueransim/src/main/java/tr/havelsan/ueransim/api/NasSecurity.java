package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.SecuredMmMessage;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class NasSecurity {

    public static NasMessage encryptNasMessage(SimulationContext ctx, NasMessage nasMessage) {
        if (nasMessage == null) {
            return null;
        }

        if (ctx.nasSecurityContext.keys.kNasEnc == null) {
            return nasMessage;
        }

        return NasEncryption.encrypt(nasMessage, ctx.nasSecurityContext, true);
    }

    public static NasMessage decryptNasMessage(SimulationContext ctx, NasMessage nasMessage) {
        if (nasMessage == null) {
            return null;
        }
        if (!(nasMessage instanceof SecuredMmMessage))
            return nasMessage;

        SecuredMmMessage securedMmMessage = (SecuredMmMessage) nasMessage;

        var decrypted = NasEncryption.decrypt(securedMmMessage, ctx.nasSecurityContext, false);
        if (decrypted == null) {
            Logging.error(Tag.NAS_SECURITY, "MAC mismatch in NAS encryption. Ignoring received NAS Message.");
            return null;
        } else {
            return decrypted;
        }
    }
}