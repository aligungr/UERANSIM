package tr.havelsan.ueransim.api.gnb;

import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.ngap.ngap_ies.PagingDRX;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.URSimUtils;

import static tr.havelsan.ueransim.ngap.Values.NGAP_Constants__id_DefaultPagingDRX;

public class GnbInterfaceManagement {

    public static void sendNgSetupRequest(GnbSimContext ctx) {
        Messaging.send2(ctx.simCtx,
                new NgapBuilder(NgapProcedure.NGSetupRequest, NgapCriticality.REJECT)
                        .addProtocolIE(URSimUtils.createGlobalGnbId(ctx.config.gnbId, ctx.config.gnbPlmn), NgapCriticality.REJECT)
                        .addProtocolIE(URSimUtils.createSupportedTAList(ctx.config.supportedTAs), NgapCriticality.REJECT)
                        .addProtocolIE(new PagingDRX(PagingDRX.ASN_v64), NgapCriticality.IGNORE, NGAP_Constants__id_DefaultPagingDRX),
                null
        );
    }

    public static void handleNgSetupResponse(GnbSimContext ctx, NGSetupResponse message) {
        // todo
    }
}
