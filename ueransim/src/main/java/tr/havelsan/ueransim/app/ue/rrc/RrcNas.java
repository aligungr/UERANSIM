/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.rrc.inners.RRC_ULInformationTransfer_CriticalExtensions;
import tr.havelsan.ueransim.rrc.messages.RrcULInformationTransfer;
import tr.havelsan.ueransim.rrc.octetstrings.RRC_DedicatedNAS_Message;
import tr.havelsan.ueransim.rrc.sequences.RRC_ULInformationTransfer_IEs;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RrcNas {

    public static void sendNas(UeRrcContext ctx, OctetString nasPdu) {
        var rrc = new RrcULInformationTransfer();
        rrc.criticalExtensions = new RRC_ULInformationTransfer_CriticalExtensions();
        rrc.criticalExtensions.ulInformationTransfer = new RRC_ULInformationTransfer_IEs();
        rrc.criticalExtensions.ulInformationTransfer.dedicatedNAS_Message = new RRC_DedicatedNAS_Message(nasPdu);

        RrcTransport.sendRrcMessage(ctx, rrc);
    }
}
