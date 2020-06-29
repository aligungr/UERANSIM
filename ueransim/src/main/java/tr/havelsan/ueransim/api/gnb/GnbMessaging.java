package tr.havelsan.ueransim.api.gnb;

import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.api.ue.UeSimContext;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

public class GnbMessaging {

    public static void sendFromUe(GnbSimContext gnbCtx, UeSimContext ueCtx, NasMessage nasMessage) {
        Messaging.send2(gnbCtx.simCtx, new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), nasMessage);
    }
}
