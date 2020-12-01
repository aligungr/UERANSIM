/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.rrc;

import tr.havelsan.ueransim.app.common.contexts.GnbRrcContext;
import tr.havelsan.ueransim.app.common.itms.IwUplinkNas;
import tr.havelsan.ueransim.rrc.RrcMessage;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_DLInformationTransfer__criticalExtensions;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_DL_DCCH_MessageType;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_DL_DCCH_MessageType__c1;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DLInformationTransfer;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DLInformationTransfer_IEs;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DL_DCCH_Message;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.UUID;

public class RrcNasTransport {

    public static void deliverDlNas(GnbRrcContext ctx, UUID ue, OctetString nasPdu) {
        var rrc = new RRC_DLInformationTransfer();
        rrc.criticalExtensions = new RRC_DLInformationTransfer__criticalExtensions();
        rrc.criticalExtensions.dlInformationTransfer = new RRC_DLInformationTransfer_IEs();
        rrc.criticalExtensions.dlInformationTransfer.dedicatedNAS_Message = new RRC_DedicatedNAS_Message(nasPdu);

        var msg = new RRC_DL_DCCH_Message();
        msg.message = new RRC_DL_DCCH_MessageType();
        msg.message.c1 = new RRC_DL_DCCH_MessageType__c1();
        msg.message.c1.dlInformationTransfer = rrc;

        RrcTransport.sendRrcMessage(ctx, ue, new RrcMessage(msg));
    }

    public static void deliverUlNas(GnbRrcContext ctx, UUID ue, OctetString nasPdu) {
        ctx.ngapTask.push(new IwUplinkNas(ue, nasPdu));
    }
}
