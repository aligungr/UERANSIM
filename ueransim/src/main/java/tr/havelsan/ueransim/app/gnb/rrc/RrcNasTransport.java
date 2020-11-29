/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.rrc;

import tr.havelsan.ueransim.app.common.contexts.GnbRrcContext;
import tr.havelsan.ueransim.rrc.inners.RRC_DLInformationTransfer_CriticalExtensions;
import tr.havelsan.ueransim.rrc.messages.RrcDLInformationTransfer;
import tr.havelsan.ueransim.rrc.octetstrings.RRC_DedicatedNAS_Message;
import tr.havelsan.ueransim.rrc.sequences.RRC_DLInformationTransfer_IEs;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.UUID;

public class RrcNasTransport {

    public static void deliverDlNas(GnbRrcContext ctx, UUID ue, OctetString nasPdu) {
        var rrc = new RrcDLInformationTransfer();
        rrc.criticalExtensions = new RRC_DLInformationTransfer_CriticalExtensions();
        rrc.criticalExtensions.dlInformationTransfer = new RRC_DLInformationTransfer_IEs();
        rrc.criticalExtensions.dlInformationTransfer.dedicatedNAS_Message = new RRC_DedicatedNAS_Message(nasPdu);

        RrcTransport.sendRrcMessage(ctx, ue, rrc);
    }

    public static void deliverUlNas(GnbRrcContext ctx, UUID ue, OctetString nasPdu) {

    }
}
