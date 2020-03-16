package tr.havelsan.ueransim.flowtesting.flows;

import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.OpenTypeValue;
import tr.havelsan.ueransim.flowtesting.inputs.DeregistrationInput;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationAcceptUeOriginating;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationRequestUeOriginating;
import tr.havelsan.ueransim.ngap.Values;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.Criticality;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.ProcedureCode;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.ProtocolIE_ID;
import tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialContextSetupResponse;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseCommand;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseComplete;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.SuccessfulOutcome;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapPduDescription;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.sim.BaseFlow;
import tr.havelsan.ueransim.sim.Message;
import tr.havelsan.ueransim.sim.ue.FlowUtils;
import tr.havelsan.ueransim.sim.ue.UeUtils;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

import java.util.ArrayList;

public class DeregistrationFlow extends BaseFlow {

    private final DeregistrationInput input;

    public DeregistrationFlow(SCTPClient sctpClient, DeregistrationInput input) {
        super(sctpClient);
        this.input = input;
    }

    @Override
    public State main(Message message) throws Exception {
        return sendDeregistrationRequest(message);
    }

    private State sendDeregistrationRequest(Message message) {
        var request = new DeRegistrationRequestUeOriginating();
        request.deRegistrationType = input.deregistrationType;
        request.ngKSI = new IENasKeySetIdentifier(IENasKeySetIdentifier.ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, input.ngKSI);
        request.mobileIdentity = input.guti;

        var ngap = new NgapBuilder()
                .withDescription(NgapPduDescription.INITIATING_MESSAGE)
                .withProcedure(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addAmfUeNgapId(input.amfUeNgapId, NgapCriticality.REJECT)
                .addNasPdu(request, NgapCriticality.REJECT)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.IGNORE)
                .build();
        sendPDU(ngap);

        return this::waitDeregistrationAccept;
    }

    private State waitDeregistrationAccept(Message message) {
        var pdu = message.getAsPDU();
        FlowUtils.logReceivedMessage(pdu);

        if (!(pdu.getValue() instanceof InitiatingMessage)) {
            Console.println(Color.YELLOW, "bad message, InitiatingMessage is expected. message ignored");
            return this::waitDeregistrationAccept;
        }

        var initiatingMessage = ((InitiatingMessage) pdu.getValue()).value.getDecodedValue();
        if (!(initiatingMessage instanceof DownlinkNASTransport)) {
            Console.println(Color.YELLOW, "bad message, DownlinkNASTransport is expected. message ignored");
            return this::waitDeregistrationAccept;
        }

        var downlinkNASTransport = (DownlinkNASTransport) initiatingMessage;
        var nasMessage = UeUtils.getNasMessage(downlinkNASTransport);
        if (nasMessage == null) {
            Console.println(Color.YELLOW, "bad message, nas pdu is missing. message ignored");
            return this::waitDeregistrationAccept;
        }

        if (!(nasMessage instanceof DeRegistrationAcceptUeOriginating)) {
            Console.println(Color.YELLOW, "bad message, DeRegistrationAcceptUeOriginating is expected. message ignored");
            return this::waitDeregistrationAccept;
        }

        return this::waitUeContextReleaseCommand;
    }

    private State waitUeContextReleaseCommand(Message message) {
        var pdu = message.getAsPDU();
        FlowUtils.logReceivedMessage(pdu);

        if (!(pdu.getValue() instanceof InitiatingMessage)) {
            Console.println(Color.YELLOW, "bad message, InitiatingMessage is expected. message ignored");
            return this::waitDeregistrationAccept;
        }

        var initiatingMessage = ((InitiatingMessage) pdu.getValue()).value.getDecodedValue();
        if (!(initiatingMessage instanceof UEContextReleaseCommand)) {
            Console.println(Color.YELLOW, "bad message, UEContextReleaseCommand is expected. message ignored");
            return this::waitDeregistrationAccept;
        }

        var command = (UEContextReleaseCommand) initiatingMessage;
        // do something with command

        return sendUeContextReleaseComplete();
    }

    private State sendUeContextReleaseComplete() {
        var list = new ArrayList<UEContextReleaseComplete.ProtocolIEs.SEQUENCE>();

        var ueContextReleaseComplete = new UEContextReleaseComplete();
        ueContextReleaseComplete.protocolIEs = new UEContextReleaseComplete.ProtocolIEs();
        ueContextReleaseComplete.protocolIEs.valueList = list;

        var item0 = new InitialContextSetupResponse.ProtocolIEs.SEQUENCE();
        item0.id = new ProtocolIE_ID(Values.NGAP_Constants__id_RAN_UE_NGAP_ID);
        item0.criticality = new Criticality(Criticality.ASN_ignore);
        item0.value = new OpenTypeValue(new RAN_UE_NGAP_ID(input.ranUeNgapId));

        var item1 = new InitialContextSetupResponse.ProtocolIEs.SEQUENCE();
        item1.id = new ProtocolIE_ID(Values.NGAP_Constants__id_AMF_UE_NGAP_ID);
        item1.criticality = new Criticality(Criticality.ASN_ignore);
        item1.value = new OpenTypeValue(new AMF_UE_NGAP_ID(input.amfUeNgapId));

        NGAP_PDU ngapPdu;
        try {
            var successfulOutcome = new SuccessfulOutcome();
            successfulOutcome.procedureCode =
                    new ProcedureCode(Values.NGAP_Constants__id_UEContextRelease);
            successfulOutcome.criticality = new Criticality(Criticality.ASN_reject);
            successfulOutcome.value = new OpenTypeValue(ueContextReleaseComplete);

            ngapPdu = new NGAP_PDU(NGAP_PDU.ASN_successfulOutcome, successfulOutcome);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }

        sendPDU(ngapPdu);

        Console.println(Color.GREEN_BOLD, "Deregistration complete");
        return abortReceiver();
    }
}
