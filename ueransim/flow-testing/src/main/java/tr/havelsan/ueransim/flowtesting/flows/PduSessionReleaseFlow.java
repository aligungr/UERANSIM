package tr.havelsan.ueransim.flowtesting.flows;

import tr.havelsan.ueransim.flowtesting.inputs.PduSessionReleaseInput;
import tr.havelsan.ueransim.sim.BaseFlow;
import tr.havelsan.ueransim.sim.Message;
import tr.havelsan.ueransim.sim.ue.FlowUtils;
import tr.havelsan.ueransim.sim.ue.UeUtils;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceReleaseCommand;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class PduSessionReleaseFlow extends BaseFlow {

    private PduSessionReleaseInput input;

    public PduSessionReleaseFlow(
            SCTPClient sctpClient, PduSessionReleaseInput pduSessionReleaseInput) {
        super(sctpClient);
        this.input = pduSessionReleaseInput;
    }

    @Override
    public State main(Message message) {
        return sendPduSessionRelease();
    }

    private State sendPduSessionRelease() {

        var nas = UeUtils.createNGAPSessionRelease(input.userLocationInformationNr);

        sendPDU(nas);
        FlowUtils.logMessageSent();
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
            return sendPduSessionResourceSetupResponse();
        }

        return this::waitPduSessionReleaseCommand;
    }

    private State sendPduSessionResourceSetupResponse() {

        var releaseResponse = UeUtils.pduSessionReleaseResponse(input.userLocationInformationNr);

        FlowUtils.logNgapMessageWillSend(releaseResponse);
        sendPDU(releaseResponse);
        FlowUtils.logMessageSent();

        var uplinkNas = UeUtils.sendUplinkNas(input.userLocationInformationNr);

        FlowUtils.logNgapMessageWillSend(uplinkNas);
        sendPDU(uplinkNas);
        FlowUtils.logMessageSent();
        Console.println(
                Color.GREEN,
                "PDU Session Resource Release completed");

        return abortReceiver();
    }
}
