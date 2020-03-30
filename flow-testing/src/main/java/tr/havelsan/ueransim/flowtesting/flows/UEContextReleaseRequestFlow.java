package tr.havelsan.ueransim.flowtesting.flows;

import static tr.havelsan.ueransim.ngap.ngap_ies.CauseMisc.ASN_om_intervention;

import fr.marben.asnsdk.japi.InvalidStructureException;
import tr.havelsan.ueransim.flowtesting.inputs.UEContextReleaseRequestInput;
import tr.havelsan.ueransim.ngap.ngap_ies.Cause;
import tr.havelsan.ueransim.ngap.ngap_ies.CauseMisc;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseCommand;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseRequest;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapPduDescription;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.sim.BaseFlow;
import tr.havelsan.ueransim.sim.Message;
import tr.havelsan.ueransim.sim.ue.FlowUtils;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class UEContextReleaseRequestFlow extends BaseFlow {

    private final UEContextReleaseRequestInput input;

    public UEContextReleaseRequestFlow(SCTPClient sctpClient, UEContextReleaseRequestInput input) {
        super(sctpClient);
        this.input = input;
    }

    @Override
    public State main(Message message) throws Exception {
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

    private State waitPduSessionReleaseCommand(Message message) {
        var pdu = message.getAsPDU();
        FlowUtils.logReceivedMessage(pdu);

        var value = ((InitiatingMessage) pdu.getValue()).value.getDecodedValue();

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
