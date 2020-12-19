/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.common.contexts.NgapGnbContext;
import tr.havelsan.ueransim.app.common.nts.IwDownlinkNas;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap0.Ngap;
import tr.havelsan.ueransim.ngap0.NgapDataUnitType;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_AMFSetID;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseMisc;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_RRCEstablishmentCause;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_UEContextRequest;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NAS_PDU;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NGAP_Message;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_AllowedNSSAI;
import tr.havelsan.ueransim.ngap0.msg.*;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.bits.Bit10;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.UUID;

public class NgapNasTransport {

    public static void receiveInitialNasTransport(NgapGnbContext ctx, UUID associatedUe, NasMessage nasMessage) {
        NgapUeManagement.createUeContext(ctx, associatedUe);

        var ngap = new NGAP_InitialUEMessage();
        ngap.addProtocolIe(NGAP_RRCEstablishmentCause.MO_DATA);
        ngap.addProtocolIe(NGAP_UEContextRequest.REQUESTED);

        var ueCtx = ctx.ueContexts.get(associatedUe);
        var amfCtx = ctx.amfContexts.get(ueCtx.associatedAmf);

        amfCtx.nextStream = (amfCtx.nextStream + 1) % amfCtx.association.outbound;
        if ((amfCtx.nextStream == 0) && (amfCtx.association.outbound > 1)) {
            amfCtx.nextStream += 1;
        }
        ueCtx.uplinkStream = amfCtx.nextStream;

        if (nasMessage != null) {
            ngap.addProtocolIe(new NGAP_NAS_PDU(NasEncoder.nasPdu(nasMessage)));
        }

        NgapTransfer.sendNgapUeAssociated(ctx, associatedUe, ngap);
    }

    public static void deliverDlNas(NgapGnbContext ctx, UUID associatedUe, OctetString nasPdu) {
        ctx.rrcTask.push(new IwDownlinkNas(associatedUe, nasPdu));
    }

    public static void receiveUplinkNasTransport(NgapGnbContext ctx, UUID associatedUe, NasMessage nasMessage) {
        var ngap = new NGAP_UplinkNASTransport();

        if (nasMessage != null) {
            ngap.addProtocolIe(new NGAP_NAS_PDU(NasEncoder.nasPdu(nasMessage)));
        }

        NgapTransfer.sendNgapUeAssociated(ctx, associatedUe, ngap);
    }

    public static void receiveDownlinkNasTransport(NgapGnbContext ctx, NGAP_DownlinkNASTransport message) {
        var associatedUe = NgapUeManagement.findAssociatedUeIdDefault(ctx, message);

        var nasMessage = message.getNasMessage();
        if (nasMessage != null) {
            deliverDlNas(ctx, associatedUe, NasEncoder.nasPduS(nasMessage));
        }
    }

    public static void sendNasNonDeliveryIndication(NgapGnbContext ctx, UUID associatedUe, OctetString nasPdu, NGAP_Enumerated cause) {
        if (cause == null)
            cause = NGAP_CauseMisc.UNSPECIFIED;

        var causeChoice = new NGAP_Cause();
        causeChoice.setPresentValue(cause);

        var ngap = new NGAP_NASNonDeliveryIndication();
        ngap.addProtocolIe(new NGAP_NAS_PDU(nasPdu));
        ngap.addProtocolIe(causeChoice);

        NgapTransfer.sendNgapUeAssociated(ctx, associatedUe, ngap);
    }

    public static void receiveRerouteNasRequest(NgapGnbContext ctx, UUID associatedAmf, NGAP_RerouteNASRequest message) {
        var associatedUe = NgapUeManagement.findAssociatedUeIdDefault(ctx, message);

        var ngapMessage = message.getProtocolIe(NGAP_NGAP_Message.class);
        var amfSetId = message.getProtocolIe(NGAP_AMFSetID.class);
        var allowedNssai = message.getProtocolIe(NGAP_AllowedNSSAI.class);

        var initialUeMessage = (NGAP_InitialUEMessage) NgapEncoding.decodeAper(ngapMessage.value, NgapDataUnitType.InitialUEMessage);

        var newMessage = Ngap.deepClone(initialUeMessage);
        if (allowedNssai != null) {
            newMessage.addProtocolIe(allowedNssai);
        }

        var newAmf = NgapUeManagement.selectNewAmfForReAllocation(ctx, associatedAmf, new Bit10(amfSetId.value.intValue()));

        if (newAmf != null) {
            Log.info(Tag.FLOW, "New AMF selected for re-allocation. AMF: %s", newAmf);

            var ueCtx = ctx.ueContexts.get(associatedUe);
            ueCtx.associatedAmf = newAmf;

            NgapTransfer.sendNgapUeAssociated(ctx, associatedUe, newMessage);
        } else {
            Log.error(Tag.FLOW, "AMF selection for re-allocation failed. Could not find a suitable AMF.");
        }
    }
}
