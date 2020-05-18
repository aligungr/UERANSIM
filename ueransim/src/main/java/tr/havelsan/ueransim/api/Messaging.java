package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.OutgoingMessage;
import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapInternal;

public class Messaging {

    public static IncomingMessage handleIncomingMessage(SimulationContext ctx, NGAP_PDU ngapPdu) {
        var ngapMessage = NgapInternal.extractNgapMessage(ngapPdu);
        var nasMessage = NgapInternal.extractNasMessage(ngapPdu);
        var decryptedNasMessage = NasSecurity.decryptNasMessage(ctx, nasMessage);
        var incomingMessage = new IncomingMessage(ngapPdu, ngapMessage, decryptedNasMessage);

        // check for AMF-UE-NGAP-ID
        {
            var ieAmfUeNgapId = NgapInternal.extractProtocolIe(ngapMessage, AMF_UE_NGAP_ID.class);
            if (ieAmfUeNgapId.size() > 0) {
                var ie = ieAmfUeNgapId.get(ieAmfUeNgapId.size() - 1);
                ctx.amfUeNgapId = ie.value;
            }
        }

        return incomingMessage;
    }

    public static OutgoingMessage handleOutgoingMessage(SimulationContext ctx, SendingMessage sendingMessage) {
        // Adding NAS PDU (if any)
        NasMessage securedNas = NasSecurity.encryptNasMessage(ctx, sendingMessage.nasMessage);
        if (securedNas != null) {
            // NOTE: criticality is hardcoded here, it may be changed
            sendingMessage.ngapBuilder.addNasPdu(securedNas, NgapCriticality.REJECT);
        }

        // Adding AMF-UE-NGAP-ID (if any)
        {
            Long amfUeNgapId = ctx.amfUeNgapId;
            if (amfUeNgapId != null) {
                // NOTE: criticality is hardcoded here, it may be changed
                sendingMessage.ngapBuilder.addAmfUeNgapId(amfUeNgapId, NgapCriticality.IGNORE);
            }
        }

        // Adding user location information
        {
            // NOTE: criticality is hardcoded here, it may be changed
            sendingMessage.ngapBuilder.addUserLocationInformationNR(ctx.userLocationInformationNr, NgapCriticality.IGNORE);
        }

        return new OutgoingMessage(sendingMessage.ngapBuilder.build(), sendingMessage.nasMessage, securedNas);
    }
}
