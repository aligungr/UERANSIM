package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.*;
import tr.havelsan.ueransim.configs.NgSetupConfig;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.ngap.ngap_ies.PagingDRX;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

import static tr.havelsan.ueransim.ngap.Values.NGAP_Constants__id_DefaultPagingDRX;

public class NgSetupFlow extends BaseFlow {
    private final NgSetupConfig config;

    public NgSetupFlow(SimulationContext simContext, NgSetupConfig config) {
        super(simContext);
        this.config = config;
    }

    @Override
    public State main(IncomingMessage message) throws Exception {
        send(new SendingMessage(
                new NgapBuilder(NgapProcedure.NGSetupRequest, NgapCriticality.REJECT)
                        .addProtocolIE(URSimUtils.createGlobalGnbId(config.gnbId, config.gnbPlmn), NgapCriticality.REJECT)
                        .addProtocolIE(URSimUtils.createSupportedTAList(config.supportedTAs), NgapCriticality.REJECT)
                        .addProtocolIE(new PagingDRX(PagingDRX.ASN_v64), NgapCriticality.IGNORE, NGAP_Constants__id_DefaultPagingDRX), null
        ));
        return this::waitNgSetupResponse;
    }

    private State waitNgSetupResponse(IncomingMessage message) {
        var ngSetupResponse = message.getNgapMessage(NGSetupResponse.class);
        if (ngSetupResponse == null) {
            FlowLogging.logUnhandledMessage(message, NGSetupResponse.class);
            return this::waitNgSetupResponse;
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
