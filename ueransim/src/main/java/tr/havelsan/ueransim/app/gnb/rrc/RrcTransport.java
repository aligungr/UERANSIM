/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.rrc;

import tr.havelsan.ueransim.app.common.contexts.GnbRrcContext;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkRrc;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Value;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ULInformationTransfer;

import java.util.UUID;

public class RrcTransport {

    public static void receiveRrcMessage(GnbRrcContext ctx, UUID ue, RRC_Value message) {
        if (message instanceof RRC_ULInformationTransfer) {
            var nasPdu = ((RRC_ULInformationTransfer) message).criticalExtensions.ulInformationTransfer
                    .dedicatedNAS_Message.value;
            RrcNasTransport.deliverUlNas(ctx, ue, nasPdu);
        }
    }

    public static void sendRrcMessage(GnbRrcContext ctx, UUID ue, RRC_Value message) {
        ctx.mrTask.push(new IwDownlinkRrc(ue, message));
    }
}
