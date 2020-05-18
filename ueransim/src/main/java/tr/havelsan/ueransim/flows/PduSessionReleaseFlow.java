package tr.havelsan.ueransim.flows;

import fr.marben.asnsdk.japi.spe.ContainingOctetStringValue;
import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.flowinputs.PduSessionReleaseInput;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.nas.impl.enums.EProcedureTransactionIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEPayloadContainer;
import tr.havelsan.ueransim.nas.impl.ies.IEPayloadContainerType;
import tr.havelsan.ueransim.nas.impl.ies.IEPduSessionIdentity2;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionReleaseComplete;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionReleaseRequest;
import tr.havelsan.ueransim.nas.impl.messages.UlNasTransport;
import tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionID;
import tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceReleaseResponseTransfer;
import tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceReleasedItemRelRes;
import tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceReleasedListRelRes;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceReleaseCommand;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.ArrayList;

public class PduSessionReleaseFlow extends BaseFlow {
    private PduSessionReleaseInput input;

    public PduSessionReleaseFlow(SimulationContext simContext, PduSessionReleaseInput pduSessionReleaseInput) {
        super(simContext);
        this.input = pduSessionReleaseInput;
    }

    @Override
    public State main(IncomingMessage message) {
        var pduRR = new PduSessionReleaseRequest();
        pduRR.pduSessionId = EPduSessionIdentity.fromValue(input.pduSessionId.intValue());
        pduRR.pti = EProcedureTransactionIdentity.fromValue(input.procedureTransactionId.intValue());

        var uplink = new UlNasTransport();
        uplink.payloadContainerType = new IEPayloadContainerType(IEPayloadContainerType.EPayloadContainerType.N1_SM_INFORMATION);
        uplink.payloadContainer = new IEPayloadContainer(new OctetString(NasEncoder.nasPdu(pduRR)));
        uplink.pduSessionId = new IEPduSessionIdentity2(input.pduSessionId);
        uplink.sNssa = input.sNssai;
        uplink.dnn = input.dnn;

        send(new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.REJECT), uplink));
        return this::waitPduSessionReleaseCommand;
    }

    private State waitPduSessionReleaseCommand(IncomingMessage message) {
        var pduSessionResourceReleaseCommand = message.getNgapMessage(PDUSessionResourceReleaseCommand.class);
        if (pduSessionResourceReleaseCommand == null) {
            logUnhandledMessage(message, PDUSessionResourceReleaseCommand.class);
            return this::waitPduSessionReleaseCommand;
        }

        sendPDUSessionResourceReleased();
        sendUplinkNas();
        return flowComplete();
    }

    private void sendPDUSessionResourceReleased() {
        var list = new PDUSessionResourceReleasedListRelRes();
        list.valueList = new ArrayList<>();

        var item = new PDUSessionResourceReleasedItemRelRes();
        item.pDUSessionID = new PDUSessionID(input.pduSessionId.intValue());
        item.pDUSessionResourceReleaseResponseTransfer = new ContainingOctetStringValue(new PDUSessionResourceReleaseResponseTransfer());

        send(new SendingMessage(new NgapBuilder(NgapProcedure.PDUSessionResourceReleaseResponse, NgapCriticality.REJECT)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.IGNORE)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.IGNORE)
                .addProtocolIE(list, NgapCriticality.IGNORE), null));
    }

    private void sendUplinkNas() {
        var releaseComplete = new PduSessionReleaseComplete();
        releaseComplete.pduSessionId = EPduSessionIdentity.fromValue(input.pduSessionId.intValue());
        releaseComplete.pti = EProcedureTransactionIdentity.fromValue(input.procedureTransactionId.intValue());

        var uplink = new UlNasTransport();
        uplink.payloadContainerType = new IEPayloadContainerType(IEPayloadContainerType.EPayloadContainerType.N1_SM_INFORMATION);
        uplink.payloadContainer = new IEPayloadContainer(new OctetString(NasEncoder.nasPdu(releaseComplete)));
        uplink.pduSessionId = new IEPduSessionIdentity2(input.pduSessionId);
        uplink.sNssa = input.sNssai;
        uplink.dnn = input.dnn;

        send(new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.REJECT), uplink));
    }
}
