/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.contexts.NgapGnbContext;
import tr.havelsan.ueransim.app.common.itms.IwNgapSend;
import tr.havelsan.ueransim.app.gnb.utils.NgapUtils;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.nas.impl.values.VTrackingAreaIdentity;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.ngap0.NgapXerEncoder;
import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UserLocationInformation;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.UUID;

public class NgapTransfer {

    public static void sendNgapNonUe(NgapGnbContext ctx, Guami associatedAmf, NGAP_BaseMessage message) {
        var ngapPdu = message.buildPdu();

        Log.debug(Tag.MSG, "Sending NGAP: %s", message.getClass().getSimpleName());
        Log.debug(Tag.MSG, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        ctx.gnbCtx.nts.findTask(ItmsId.GNB_TASK_SCTP).push(new IwNgapSend(0, NgapEncoding.encodeAper(ngapPdu), associatedAmf));
        ctx.gnbCtx.sim.triggerOnSend(ctx.gnbCtx, message);
    }

    public static void sendNgapUeAssociated(NgapGnbContext ctx, UUID ueId, NGAP_BaseMessage message) {
        var ueCtx = ctx.ueContexts.get(ueId);

        // Adding AMF-UE-NGAP-ID (if any)
        if (message.isProtocolIeUsable(NGAP_AMF_UE_NGAP_ID.class)) {
            Long amfUeNgapId = ueCtx.amfUeNgapId;
            if (amfUeNgapId != null) {
                message.addProtocolIe(new NGAP_AMF_UE_NGAP_ID(amfUeNgapId));
            }
        }

        // Adding RAN-UE-NGAP-ID
        if (message.isProtocolIeUsable(NGAP_RAN_UE_NGAP_ID.class)) {
            message.addProtocolIe(new NGAP_RAN_UE_NGAP_ID(ueCtx.ranUeNgapId));
        }

        // Adding user location information
        if (message.isProtocolIeUsable(NGAP_UserLocationInformation.class)) {
            var ie = new NGAP_UserLocationInformation();
            ie.userLocationInformationNR = NgapUtils.createUserLocationInformationNr(ctx.gnbCtx.config.gnbPlmn,
                    new VTrackingAreaIdentity(ctx.gnbCtx.config.gnbPlmn, ctx.gnbCtx.config.tac), ctx.gnbCtx.config.nci);
            message.addProtocolIe(ie);
        }

        var ngapPdu = message.buildPdu();

        Log.debug(Tag.MSG, "Sending NGAP: %s", message.getClass().getSimpleName());
        Log.debug(Tag.MSG, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        ctx.gnbCtx.nts.findTask(ItmsId.GNB_TASK_SCTP).push(new IwNgapSend(ueCtx.uplinkStream, NgapEncoding.encodeAper(ngapPdu), ueCtx.associatedAmf));
        ctx.gnbCtx.sim.triggerOnSend(ctx.gnbCtx, message);
    }
}
