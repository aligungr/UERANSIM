package tr.havelsan.ueransim.api.ue;

import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.SendingMessage;

public class UeMessaging {

    public static void send(UeSimContext ctx, NasMessage message) {
        Messaging.send2(ctx.simCtx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), message));
    }
}
