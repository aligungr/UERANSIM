/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.gnb.utils.NgapUtils;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingDRX;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupFailure;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupResponse;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class NgapInterfaceManagement {

    public static void sendNgSetupRequest(GnbSimContext ctx, Guami associatedAmf) {
        Log.funcIn("Starting: NGSetupRequest");
        Log.info(Tag.PROC, "NGSetup procedure is starting");

        var msg = new NGAP_NGSetupRequest();
        msg.addProtocolIe(NgapUtils.createGlobalGnbId(ctx.config.gnbId, ctx.config.gnbPlmn));
        msg.addProtocolIe(NgapUtils.createSupportedTAList(ctx.config.supportedTAs));
        msg.addProtocolIe(NGAP_PagingDRX.V64);

        NgapTransfer.sendNgapNonUe(ctx, associatedAmf, msg);
        Log.funcOut();
    }

    public static void receiveNgSetupResponse(GnbSimContext ctx, NGAP_NGSetupResponse message) {
        Log.funcIn("Handling: NGSetupResponse");
        Log.success(Tag.PROC, "NGSetup procedure is successful");

        Log.funcOut();
    }

    public static void receiveNgSetupFailure(GnbSimContext ctx, NGAP_NGSetupFailure message) {
        Log.funcIn("Handling: NGSetupFailure");
        Log.error(Tag.PROC, "NGSetup procedure is failed");

        Log.funcOut();
    }
}
