package tr.havelsan.ueransim.flowtesting.flows;

import tr.havelsan.ueransim.flowtesting.inputs.PduSessionEstablishmentInput;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapPduDescription;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.sim.BaseFlow;
import tr.havelsan.ueransim.sim.Message;
import tr.havelsan.ueransim.sim.ue.FlowUtils;
import tr.havelsan.ueransim.sim.ue.UeUtils;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.nas.impl.enums.EProcedureTransactionIdentity;
import tr.havelsan.ueransim.nas.impl.ies.*;
import tr.havelsan.ueransim.nas.impl.ies.IEIntegrityProtectionMaximumDataRate.EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink;
import tr.havelsan.ueransim.nas.impl.ies.IEIntegrityProtectionMaximumDataRate.EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink;
import tr.havelsan.ueransim.nas.impl.ies.IEPayloadContainerType.EPayloadContainerType;
import tr.havelsan.ueransim.nas.impl.ies.IERequestType.ERequestType;
import tr.havelsan.ueransim.nas.impl.ies.IESscMode.ESscMode;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionEstablishmentRequest;
import tr.havelsan.ueransim.nas.impl.messages.UlNasTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceSetupRequest;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class PduSessionEstablishmentFlow extends BaseFlow {

    private final PduSessionEstablishmentInput input;

    public PduSessionEstablishmentFlow(SCTPClient sctpClient, PduSessionEstablishmentInput input) {
        super(sctpClient);
        this.input = input;
    }

    @Override
    public State main(Message message) {
        return sendEstablishmentRequest();
    }

    private State sendEstablishmentRequest() {
        var pduSessionEstablishmentRequest = new PduSessionEstablishmentRequest();
        pduSessionEstablishmentRequest.pduSessionId =
                EPduSessionIdentity.fromValue(input.pduSessionId.intValue());
        pduSessionEstablishmentRequest.pti =
                EProcedureTransactionIdentity.fromValue(input.procedureTransactionId.intValue());
        pduSessionEstablishmentRequest.integrityProtectionMaximumDataRate =
                new IEIntegrityProtectionMaximumDataRate(
                        EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink.FULL_DATA_RATE,
                        EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink.FULL_DATA_RATE);
        pduSessionEstablishmentRequest.pduSessionType = new IEPduSessionType(EPduSessionType.IPV4);
        pduSessionEstablishmentRequest.sscMode = new IESscMode(ESscMode.SSC_MODE_1);

        var ulNasTransport = new UlNasTransport();
        ulNasTransport.payloadContainerType =
                new IEPayloadContainerType(EPayloadContainerType.N1_SM_INFORMATION);
        ulNasTransport.payloadContainer =
                new IEPayloadContainer(new OctetString(NasEncoder.nasPdu(pduSessionEstablishmentRequest)));
        ulNasTransport.pduSessionId = new IEPduSessionIdentity2(input.pduSessionId);
        ulNasTransport.requestType = new IERequestType(ERequestType.INITIAL_REQUEST);
        ulNasTransport.sNssa = input.sNssai;
        ulNasTransport.dnn = input.dnn;

        var ngap = new NgapBuilder()
                .withDescription(NgapPduDescription.INITIATING_MESSAGE)
                .withProcedure(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addAmfUeNgapId(input.amfUeNgapId, NgapCriticality.REJECT)
                .addNasPdu(ulNasTransport, NgapCriticality.REJECT)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.IGNORE)
                .build();
        sendPDU(ngap);

        return this::waitPduSessionEstablishmentAccept;
    }

    private State waitPduSessionEstablishmentAccept(Message message) {
        var pdu = message.getAsPDU();
        FlowUtils.logReceivedMessage(pdu);

        if (!(pdu.getValue() instanceof InitiatingMessage)) {
            Console.println(Color.YELLOW, "bad message, InitiatingMessage is expected. message ignored");
            return this::waitPduSessionEstablishmentAccept;
        }

        var initiatingMessage = (InitiatingMessage) pdu.getValue();
        if (!(initiatingMessage.value.getDecodedValue() instanceof PDUSessionResourceSetupRequest)) {
            Console.println(
                    Color.YELLOW, "bad message, PDUSessionResourceSetupRequest is expected. message ignored");
            return this::waitPduSessionEstablishmentAccept;
        }

        return sendPduSessionResourceSetupResponse();
    }

    private State sendPduSessionResourceSetupResponse() {
        var ngap = UeUtils.createNGAPSuccesfullOutCome();

        sendPDU(ngap);

        FlowUtils.logMessageSent();
        Console.println(Color.BLUE, "PDU Session Establishment Completed");
        return abortReceiver();
    }
}
