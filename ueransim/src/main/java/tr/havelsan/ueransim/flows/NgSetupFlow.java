package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.URSimUtils;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.flowinputs.NgSetupInput;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class NgSetupFlow extends BaseFlow {
    private final NgSetupInput input;

    public NgSetupFlow(SimulationContext simContext, NgSetupInput input) {
        super(simContext);
        this.input = input;
    }

    @Override
    public State main(IncomingMessage message) throws Exception {
        sendNgap(URSimUtils.createNgSetupRequest(input.gnbId, input.gnbPlmn, input.supportedTAs));
        return this::waitNgSetupResponse;
    }

    private State waitNgSetupResponse(IncomingMessage message) {
        var ngSetupResponse = message.getNgapMessage(NGSetupResponse.class);
        if (ngSetupResponse == null) {
            Console.println(Color.YELLOW, "bad message, NGSetupResponse is expected. message ignored");
            return this::waitNgSetupResponse;
        }

        logFlowComplete();
        return abortReceiver();
    }
}
