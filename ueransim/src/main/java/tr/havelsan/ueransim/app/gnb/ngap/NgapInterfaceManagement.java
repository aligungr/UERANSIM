/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.contexts.NgapGnbContext;
import tr.havelsan.ueransim.app.common.enums.EAmfState;
import tr.havelsan.ueransim.app.gnb.utils.NgapUtils;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingDRX;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RelativeAMFCapacity;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.NGAP_AMFName;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.NGAP_RANNodeName;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_PLMNSupportList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_ServedGUAMIList;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupFailure;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupResponse;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class NgapInterfaceManagement {

    public static void sendNgSetupRequest(NgapGnbContext ctx, Guami associatedAmf) {
        // TODO: this procedure also re-initialises the NGAP UE-related contexts (if any)
        //  and erases all related signalling connections in the two nodes like an NG Reset procedure would do.
        //  More on 38.413 8.7.1.1

        Log.funcIn("Starting: NGSetupRequest");
        Log.info(Tag.PROC, "NGSetup procedure is starting");

        var amf = ctx.amfContexts.get(associatedAmf);
        if (amf == null) {
            Log.error(Tag.PROC, "Cannot send NG Setup Request: AMF context not found.");
            Log.funcOut();
            return;
        }

        amf.state = EAmfState.WAITING_NG_SETUP;

        var msg = new NGAP_NGSetupRequest();
        msg.addProtocolIe(NgapUtils.createGlobalGnbId(ctx.gnbCtx.config.gnbId, ctx.gnbCtx.config.gnbPlmn));
        msg.addProtocolIe(NgapUtils.createSupportedTAList(ctx.gnbCtx.config.supportedTAs));
        msg.addProtocolIe(new NGAP_RANNodeName("UERANSIM/gnb" + ctx.gnbCtx.config.gnbId));
        msg.addProtocolIe(NGAP_PagingDRX.V64);

        NgapTransfer.sendNgapNonUe(ctx, associatedAmf, msg);
        Log.funcOut();
    }

    public static void receiveNgSetupResponse(NgapGnbContext ctx, Guami associatedAmf, NGAP_NGSetupResponse message) {
        Log.funcIn("Handling: NGSetupResponse");

        var amf = ctx.amfContexts.get(associatedAmf);
        if (amf == null) {
            Log.error(Tag.PROC, "NGSetup procedure is failed: AMF context not found.");
            Log.funcOut();
            return;
        }

        amf.amfName = message.getProtocolIe(NGAP_AMFName.class).value;
        amf.relativeCapacity = (int) message.getProtocolIe(NGAP_RelativeAMFCapacity.class).value;
        amf.servedGuamiList = message.getProtocolIe(NGAP_ServedGUAMIList.class).list;
        amf.plmnSupportList = message.getProtocolIe(NGAP_PLMNSupportList.class).list;
        amf.state = EAmfState.CONNECTED;

        Log.success(Tag.PROC, "NGSetup procedure is successful");
        Log.funcOut();
    }

    public static void receiveNgSetupFailure(NgapGnbContext ctx, NGAP_NGSetupFailure message) {
        Log.funcIn("Handling: NGSetupFailure");
        Log.error(Tag.PROC, "NGSetup procedure is failed");

        Log.funcOut();
    }
}
