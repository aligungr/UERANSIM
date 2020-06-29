package tr.havelsan.ueransim.api.gnb;

import tr.havelsan.ueransim.api.ue.UeMessaging;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap2.NgapInternal;

public class GnbNasTransport {

    public static void handleDownlinkNasTransport(GnbSimContext ctx, DownlinkNASTransport message) {
        var nasMessage = NgapInternal.extractNasMessage(message);
        if (nasMessage != null) {
            UeMessaging.handleNas(ctx.simCtx.ue, nasMessage);
        }
    }
}
