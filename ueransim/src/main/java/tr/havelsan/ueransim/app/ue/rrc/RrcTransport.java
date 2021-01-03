/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.app.common.nts.IwDownlinkNas;
import tr.havelsan.ueransim.app.common.nts.IwUplinkRrc;
import tr.havelsan.ueransim.rrc.RrcMessage;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_UL_DCCH_MessageType;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ULInformationTransfer;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ULInformationTransfer_IEs;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_UL_DCCH_Message;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RrcTransport {

    public static void receiveRrcMessage(UeRrcContext ctx, RrcMessage message) {
        if (message.dlInformationTransfer != null) {
            var nasPdu = message.dlInformationTransfer.criticalExtensions
                    .dlInformationTransfer.dedicatedNAS_Message.value;
            ctx.nasTask.push(new IwDownlinkNas(ctx.ueCtx.ctxId, nasPdu));
        }
    }

    public static void sendRrcMessage(UeRrcContext ctx, RrcMessage message) {
        ctx.mrTask.push(new IwUplinkRrc(ctx.ueCtx.ctxId, message));
    }

    public static void deliverUplinkNas(UeRrcContext ctx, OctetString nasPdu) {
        var rrc = new RRC_ULInformationTransfer();
        rrc.criticalExtensions = new RRC_ULInformationTransfer.RRC_criticalExtensions_4();
        rrc.criticalExtensions.ulInformationTransfer = new RRC_ULInformationTransfer_IEs();
        rrc.criticalExtensions.ulInformationTransfer.dedicatedNAS_Message = new RRC_DedicatedNAS_Message(nasPdu);

        var msg = new RRC_UL_DCCH_Message();
        msg.message = new RRC_UL_DCCH_MessageType();
        msg.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
        msg.message.c1.ulInformationTransfer = rrc;

        RrcTransport.sendRrcMessage(ctx, new RrcMessage(rrc));
    }
}
