/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.rrc;

import tr.havelsan.ueransim.app.common.contexts.GnbRrcContext;
import tr.havelsan.ueransim.app.common.nts.IwUplinkNas;
import tr.havelsan.ueransim.rrc.RrcMessage;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DLInformationTransfer;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DLInformationTransfer_IEs;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.UUID;

public class RrcNasTransport {

    public static void deliverDlNas(GnbRrcContext ctx, UUID ue, OctetString nasPdu) {
        var rrc = new RRC_DLInformationTransfer();
        rrc.criticalExtensions = new RRC_DLInformationTransfer.RRC_criticalExtensions_10();
        rrc.criticalExtensions.dlInformationTransfer = new RRC_DLInformationTransfer_IEs();
        rrc.criticalExtensions.dlInformationTransfer.dedicatedNAS_Message = new RRC_DedicatedNAS_Message(nasPdu);

        RrcTransport.sendRrcMessage(ctx, ue, new RrcMessage(rrc));
    }

    public static void deliverUlNas(GnbRrcContext ctx, UUID ue, OctetString nasPdu) {
        ctx.ngapTask.push(new IwUplinkNas(ue, nasPdu));
    }
}
