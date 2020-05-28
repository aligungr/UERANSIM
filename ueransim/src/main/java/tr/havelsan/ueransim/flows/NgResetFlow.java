package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.*;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.ngap.ngap_ies.Cause;
import tr.havelsan.ueransim.ngap.ngap_ies.CauseRadioNetwork;
import tr.havelsan.ueransim.ngap.ngap_ies.ResetAll;
import tr.havelsan.ueransim.ngap.ngap_ies.ResetType;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGReset;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGResetAcknowledge;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCause;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

import java.util.ArrayList;

import static tr.havelsan.ueransim.ngap.ngap_ies.Cause.ASN_radioNetwork;
import static tr.havelsan.ueransim.ngap.ngap_ies.ResetType.ASN_nG_Interface;

public class NgResetFlow extends BaseFlow {

    public NgResetFlow(SimulationContext simContext) {
        super(simContext);
    }

    @Override
    public State main(IncomingMessage message) throws Exception {

        var causeRadioNetwork = new CauseRadioNetwork(CauseRadioNetwork.ASN_user_inactivity);
        var cause = new Cause(ASN_radioNetwork,causeRadioNetwork);
        var ngapCause = NgapCause.RADIO_NETWORK_USER_INACTIVITY;

        var ngReset = new NGReset();
        ngReset.protocolIEs = new NGReset.ProtocolIEs();
        ngReset.protocolIEs.valueList = new ArrayList();
        ngReset.protocolIEs.valueList.add(cause);

        var resetAll = new ResetAll();
        var resetType = new ResetType(ASN_nG_Interface,resetAll);

        ngReset.protocolIEs.valueList.add(resetType);

        send(new SendingMessage(
                new NgapBuilder(NgapProcedure.NGReset, NgapCriticality.REJECT).addCause(ngapCause, NgapCriticality.IGNORE).addProtocolIE(resetType,NgapCriticality.REJECT), null
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
