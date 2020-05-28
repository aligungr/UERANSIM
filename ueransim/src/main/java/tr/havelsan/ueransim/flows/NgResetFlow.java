package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.*;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.ngap.ngap_ies.ResetAll;
import tr.havelsan.ueransim.ngap.ngap_ies.ResetType;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGResetAcknowledge;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCause;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

import static tr.havelsan.ueransim.ngap.ngap_ies.ResetType.ASN_nG_Interface;

public class NgResetFlow extends BaseFlow {

    public NgResetFlow(SimulationContext simContext) {
        super(simContext);
    }

    @Override
    public State main(IncomingMessage message) throws Exception {
        var ngapCause = NgapCause.RADIO_NETWORK_USER_INACTIVITY;

        var resetAll = new ResetAll();
        var resetType = new ResetType(ASN_nG_Interface, resetAll);

        send(new SendingMessage(
                new NgapBuilder(NgapProcedure.NGReset, NgapCriticality.REJECT)
                        .addCause(ngapCause, NgapCriticality.IGNORE)
                        .addProtocolIE(resetType, NgapCriticality.REJECT), null
        ));

        return this::waitNgResetAcknowledge;
    }

    private State waitNgResetAcknowledge(IncomingMessage message) {
        var ngSetupResponse = message.getNgapMessage(NGResetAcknowledge.class);
        if (ngSetupResponse == null) {
            FlowLogging.logUnhandledMessage(message, NGResetAcknowledge.class);
            return this::waitNgResetAcknowledge;
        }

        return flowComplete();
    }

    @Override
    public void onReceive(IncomingMessage incomingMessage) {

    }

    @Override
    public void onSent(OutgoingMessage outgoingMessage) {

    }
}
