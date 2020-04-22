package tr.havelsan.ueransim.flows;

import fr.marben.asnsdk.japi.InvalidStructureException;
import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.flowinputs.UEContextReleaseRequestInput;
import tr.havelsan.ueransim.ngap.ngap_ies.Cause;
import tr.havelsan.ueransim.ngap.ngap_ies.CauseMisc;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseCommand;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapPduDescription;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

import static tr.havelsan.ueransim.ngap.ngap_ies.CauseMisc.ASN_om_intervention;

public class UEContextReleaseRequestFlow extends BaseFlow {

    private final UEContextReleaseRequestInput input;

    public UEContextReleaseRequestFlow(SimulationContext simContext, UEContextReleaseRequestInput input) {
        super(simContext);
        this.input = input;
    }

    @Override
    public State main(NGAP_PDU ngapIn) throws Exception {
        return sendRequest();
    }

    private State sendRequest() throws InvalidStructureException {
        var misc = new CauseMisc(ASN_om_intervention);
        var cause = new Cause(Cause.ASN_misc, misc);

        var ngSetupRequest = new NgapBuilder()
                .withDescription(NgapPduDescription.INITIATING_MESSAGE)
                .withProcedure(NgapProcedure.UEContextReleaseRequest, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addAmfUeNgapId(input.amfUeNgapId, NgapCriticality.REJECT)
                .addProtocolIE(cause, NgapCriticality.IGNORE)
                .build();

        sendPDU(ngSetupRequest);
        return this::waitPduSessionReleaseCommand;
    }

    private State waitPduSessionReleaseCommand(NGAP_PDU ngapIn) {
        logReceivedMessage(ngapIn);

        var value = ((InitiatingMessage) ngapIn.getValue()).value.getDecodedValue();

        if (value instanceof UEContextReleaseCommand) {
            Console.println(Color.BLUE, "UEContextReleaseCommand arrived, UEContextReleaseComplete will return");
            return ueContextReleaseComplete();
        } else {
            Console.println(Color.YELLOW, "unexpected message ignored");
            return this::waitPduSessionReleaseCommand;
        }
    }

    private State ueContextReleaseComplete() {
        var ngSetupRequest = new NgapBuilder()
                .withDescription(NgapPduDescription.SUCCESSFUL_OUTCOME)
                .withProcedure(NgapProcedure.UEContextReleaseComplete, NgapCriticality.REJECT)
                .addAmfUeNgapId(input.amfUeNgapId, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .build();
        sendPDU(ngSetupRequest);

        Console.println(Color.GREEN, "UEContextRelease completed");
        return abortReceiver();
    }
}
