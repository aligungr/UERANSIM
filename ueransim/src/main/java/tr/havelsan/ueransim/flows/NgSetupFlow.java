package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.Message;
import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.app.Json;
import tr.havelsan.ueransim.app.ue.UeUtils;
import tr.havelsan.ueransim.inputs.NgSetupInput;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.SuccessfulOutcome;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Utils;

public class NgSetupFlow extends BaseFlow {
    private final NgSetupInput input;

    public NgSetupFlow(NgSetupInput input) {
        this.input = input;
    }

    @Override
    public State main(Message message) throws Exception {
        return sendNgSetupRequest();
    }

    private State sendNgSetupRequest() {
        var ngSetupRequest = UeUtils.createNgSetupRequest(input.gnbId, input.gnbPlmn,
                input.supportedTAs);
        sendNgapMessage(ngSetupRequest);
        return this::waitNgSetupResponse;
    }

    private State waitNgSetupResponse(Message message) {
        var pdu = message.getAsPDU();
        logReceivedMessage(pdu);

        if (!(pdu.getValue() instanceof SuccessfulOutcome)) {
            Console.println(Color.YELLOW, "bad message, SuccessfulOutcome is expected. message ignored");
            return this::waitNgSetupResponse;
        }

        var successfulOutcome = (SuccessfulOutcome) pdu.getValue();
        if (!(successfulOutcome.value.getDecodedValue() instanceof NGSetupResponse)) {
            Console.println(Color.YELLOW, "bad message, NGSetupResponse is expected. message ignored");
            return this::waitNgSetupResponse;
        }

        Console.println(Color.BLUE, "NGSetupResponse handled.");
        Console.println(Color.GREEN_BOLD, "NGSetup complete");
        return closeConnection();
    }

    public void logNasMessageWillSend(NasMessage nasMessage) {
        Console.printDiv();
        Console.println(Color.BLUE, nasMessage.getClass().getSimpleName() + " will be sent");
        Console.println(Color.BLUE, "While NAS message is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(nasMessage));
    }

    public void sendNgapMessage(NGAP_PDU ngapPdu) {
        Console.printDiv();
        Console.println(Color.BLUE, "Following NGAP message will be sent:");
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(ngapPdu)));

        sendPDU(ngapPdu);

        Console.println(Color.BLUE, "Message sent");
    }

    public void logReceivedMessage(NGAP_PDU ngapPdu) {
        Console.printDiv();
        Console.println(Color.BLUE, "Received NGAP PDU:");
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(ngapPdu)));
    }
}
