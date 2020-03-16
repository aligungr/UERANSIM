package tr.havelsan.ueransim.flowtesting.flows;

import fr.marben.asnsdk.japi.spe.ContainingOctetStringValue;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapPduDescription;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.flowtesting.inputs.PduSessionReleaseInput;
import tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionID;
import tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceReleaseResponseTransfer;
import tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceReleasedItemRelRes;
import tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceReleasedListRelRes;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceReleaseCommand;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.sim.BaseFlow;
import tr.havelsan.ueransim.sim.Message;
import tr.havelsan.ueransim.sim.ue.FlowUtils;
import tr.havelsan.ueransim.sim.ue.UeUtils;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

import java.util.ArrayList;

public class PduSessionReleaseFlow extends BaseFlow {
    private PduSessionReleaseInput input;

    public PduSessionReleaseFlow(SCTPClient sctpClient, PduSessionReleaseInput pduSessionReleaseInput) {
        super(sctpClient);
        this.input = pduSessionReleaseInput;
    }

    @Override
    public State main(Message message) {
        sendPDU(UeUtils.createNGAPSessionRelease(input.userLocationInformationNr));
        return this::waitPduSessionReleaseCommand;
    }

    private State waitPduSessionReleaseCommand(Message message) {
        var pdu = message.getAsPDU();
        FlowUtils.logReceivedMessage(pdu);

        var value = ((InitiatingMessage) pdu.getValue()).value.getDecodedValue();

        if (value instanceof PDUSessionResourceReleaseCommand) {
            Console.println(
                    Color.BLUE,
                    "PDUSessionResourceReleaseCommand arrived, Pdu Session Resource Setup Response will return.");
            return sendResponse();
        } else {
            Console.println(
                    Color.YELLOW,
                    "PDUSessionResourceReleaseCommand was expected, message ignored");
            return this::waitPduSessionReleaseCommand;
        }
    }

    private State sendResponse() {
        sendPDUSessionResourceReleased();

        var uplinkNas = UeUtils.sendUplinkNas(input.userLocationInformationNr);
        sendPDU(uplinkNas);

        Console.println(Color.GREEN, "PDU Session Resource Release completed");
        return abortReceiver();
    }

    private void sendPDUSessionResourceReleased() {
        var list = new PDUSessionResourceReleasedListRelRes();
        list.valueList = new ArrayList<>();

        var item = new PDUSessionResourceReleasedItemRelRes();
        item.pDUSessionID = new PDUSessionID(8);
        item.pDUSessionResourceReleaseResponseTransfer = new ContainingOctetStringValue(new PDUSessionResourceReleaseResponseTransfer());

        var releaseResponse = new NgapBuilder()
                .withDescription(NgapPduDescription.SUCCESSFUL_OUTCOME)
                .withProcedure(NgapProcedure.PDUSessionResourceReleaseResponse, NgapCriticality.REJECT)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.IGNORE)
                .addAmfUeNgapId(input.amfUeNgapId, NgapCriticality.IGNORE)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.IGNORE)
                .addProtocolIE(list, NgapCriticality.IGNORE)
                .build();

        sendPDU(releaseResponse);
    }
}
