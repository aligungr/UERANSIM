package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.flowinputs.UEContextReleaseRequestInput;
import tr.havelsan.ueransim.ngap.ngap_ies.Cause;
import tr.havelsan.ueransim.ngap.ngap_ies.CauseMisc;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseCommand;
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
    public State main(IncomingMessage message) throws Exception {
        var misc = new CauseMisc(ASN_om_intervention);
        var cause = new Cause(Cause.ASN_misc, misc);

        send(new NgapBuilder()
                .withDescription(NgapPduDescription.INITIATING_MESSAGE)
                .withProcedure(NgapProcedure.UEContextReleaseRequest, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addAmfUeNgapId(input.amfUeNgapId, NgapCriticality.REJECT)
                .addProtocolIE(cause, NgapCriticality.IGNORE), null);
        return this::waitPduSessionReleaseCommand;
    }

    private State waitPduSessionReleaseCommand(IncomingMessage message) {
        var ueContextReleaseCommand = message.getNgapMessage(UEContextReleaseCommand.class);
        if (ueContextReleaseCommand == null) {
            Console.println(Color.YELLOW, "unexpected message ignored");
            return this::waitPduSessionReleaseCommand;
        }

        send(new NgapBuilder()
                .withDescription(NgapPduDescription.SUCCESSFUL_OUTCOME)
                .withProcedure(NgapProcedure.UEContextReleaseComplete, NgapCriticality.REJECT)
                .addAmfUeNgapId(input.amfUeNgapId, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT), null);

        return flowComplete();
    }
}
