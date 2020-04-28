package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.URSimUtils;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.flowinputs.NgSetupInput;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.SuccessfulOutcome;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class NgSetupFlow extends BaseFlow {
    private final NgSetupInput input;

    public NgSetupFlow(SimulationContext simContext, NgSetupInput input) {
        super(simContext);
        this.input = input;
    }

    @Override
    public State main(NGAP_PDU ngapIn) throws Exception {
        sendNgap(URSimUtils.createNgSetupRequest(input.gnbId, input.gnbPlmn, input.supportedTAs));
        return this::waitNgSetupResponse;
    }

    private State waitNgSetupResponse(NGAP_PDU ngapIn) {
        if (!(ngapIn.getValue() instanceof SuccessfulOutcome)) {
            Console.println(Color.YELLOW, "bad message, SuccessfulOutcome is expected. message ignored");
            return this::waitNgSetupResponse;
        }

        var successfulOutcome = (SuccessfulOutcome) ngapIn.getValue();
        if (!(successfulOutcome.value.getDecodedValue() instanceof NGSetupResponse)) {
            Console.println(Color.YELLOW, "bad message, NGSetupResponse is expected. message ignored");
            return this::waitNgSetupResponse;
        }

        logFlowComplete();
        return abortReceiver();
    }
}
