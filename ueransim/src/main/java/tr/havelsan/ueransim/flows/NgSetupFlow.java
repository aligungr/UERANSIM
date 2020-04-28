package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.URSimUtils;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.flowinputs.NgSetupInput;
import tr.havelsan.ueransim.ngap.ngap_ies.PagingDRX;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

import static tr.havelsan.ueransim.ngap.Values.NGAP_Constants__id_DefaultPagingDRX;

public class NgSetupFlow extends BaseFlow {
    private final NgSetupInput input;

    public NgSetupFlow(SimulationContext simContext, NgSetupInput input) {
        super(simContext);
        this.input = input;
    }

    @Override
    public State main(IncomingMessage message) throws Exception {
        send(new NgapBuilder(NgapProcedure.NGSetupRequest, NgapCriticality.REJECT)
                .addProtocolIE(URSimUtils.createGlobalGnbId(input.gnbId, input.gnbPlmn), NgapCriticality.REJECT)
                .addProtocolIE(URSimUtils.createSupportedTAList(input.supportedTAs), NgapCriticality.REJECT)
                .addProtocolIE(new PagingDRX(PagingDRX.ASN_v64), NgapCriticality.IGNORE, NGAP_Constants__id_DefaultPagingDRX), null);
        return this::waitNgSetupResponse;
    }

    private State waitNgSetupResponse(IncomingMessage message) {
        var ngSetupResponse = message.getNgapMessage(NGSetupResponse.class);
        if (ngSetupResponse == null) {
            Console.println(Color.YELLOW, "bad message, NGSetupResponse is expected. message ignored");
            return this::waitNgSetupResponse;
        }

        return flowComplete();
    }
}
