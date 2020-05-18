package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.FlowLogging;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.configs.UEContextReleaseRequestConfig;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.ngap.ngap_ies.Cause;
import tr.havelsan.ueransim.ngap.ngap_ies.CauseMisc;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseCommand;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

import static tr.havelsan.ueransim.ngap.ngap_ies.CauseMisc.ASN_om_intervention;

public class UEContextReleaseRequestFlow extends BaseFlow {

    private final UEContextReleaseRequestConfig config;

    public UEContextReleaseRequestFlow(SimulationContext simContext, UEContextReleaseRequestConfig config) {
        super(simContext);
        this.config = config;
    }

    @Override
    public State main(IncomingMessage message) throws Exception {
        var misc = new CauseMisc(ASN_om_intervention);
        var cause = new Cause(Cause.ASN_misc, misc);

        send(new SendingMessage(new NgapBuilder(NgapProcedure.UEContextReleaseRequest, NgapCriticality.IGNORE)
                .addProtocolIE(cause, NgapCriticality.IGNORE), null));
        return this::waitPduSessionReleaseCommand;
    }

    private State waitPduSessionReleaseCommand(IncomingMessage message) {
        var ueContextReleaseCommand = message.getNgapMessage(UEContextReleaseCommand.class);
        if (ueContextReleaseCommand == null) {
            FlowLogging.logUnhandledMessage(message, UEContextReleaseCommand.class);
            return this::waitPduSessionReleaseCommand;
        }

        send(new SendingMessage(new NgapBuilder(NgapProcedure.UEContextReleaseComplete, NgapCriticality.REJECT), null));

        return flowComplete();
    }
}
