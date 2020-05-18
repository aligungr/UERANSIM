package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.SecuredMmMessage;

public class NasSecurity {

    public static NasMessage encryptNasMessage(SimulationContext ctx, NasMessage nasMessage) {
        if (nasMessage == null) {
            return null;
        }

        // todo: encrypt nasMessage if needed
        return nasMessage;
    }

    public static NasMessage decryptNasMessage(SimulationContext ctx, NasMessage nasMessage) {
        if (nasMessage == null) {
            return null;
        }
        if (!(nasMessage instanceof SecuredMmMessage))
            return nasMessage;

        SecuredMmMessage securedMmMessage = (SecuredMmMessage) nasMessage;

        /*if (ctx.nasSecurityContext != null) {
            NasEncryption.decrypt(securedMmMessage, ctx.nasSecurityContext, false);
        } else {

        }*/

        // todo: decrypt nasMessage if needed
        return nasMessage;
    }
}