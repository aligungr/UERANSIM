package tr.havelsan.ueransim.api.gnb;

import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.api.ue.UeSimContext;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialContextSetupRequest;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapInternal;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.FlowLogging;

public class GnbMessaging {

    public static void sendFromUe(GnbSimContext gnbCtx, UeSimContext ueCtx, NasMessage nasMessage) {
        Messaging.send2(gnbCtx.simCtx, new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), nasMessage);
    }

    public static void handleFromNetwork(GnbSimContext ctx, NGAP_PDU ngapPdu) {
        var ngapMessage = NgapInternal.extractNgapMessage(ngapPdu);

        // check for AMF-UE-NGAP-ID
        {
            var ieAmfUeNgapId = NgapInternal.extractProtocolIe(ngapMessage, AMF_UE_NGAP_ID.class);
            if (ieAmfUeNgapId.size() > 0) {
                var ie = ieAmfUeNgapId.get(ieAmfUeNgapId.size() - 1);
                ctx.amfUeNgapId = ie.value;
            }
        }

        if (ngapMessage instanceof NGSetupResponse) {
            GnbInterfaceManagement.handleNgSetupResponse(ctx, (NGSetupResponse) ngapMessage);
        } else if (ngapMessage instanceof DownlinkNASTransport) {
            GnbNasTransport.handleDownlinkNasTransport(ctx, (DownlinkNASTransport) ngapMessage);
        } else if (ngapMessage instanceof InitialContextSetupRequest) {
            GnbUeContextManagement.handleInitialContextSetup(ctx, (InitialContextSetupRequest) ngapMessage);
        } else {
            FlowLogging.logUnhandledMessage(ngapMessage.getClass().getSimpleName());
        }
    }
}
