package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.flowinputs.ServiceRequestFlowInput;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.nas.impl.ies.IEServiceType;
import tr.havelsan.ueransim.nas.impl.ies.IEServiceType.EServiceType;
import tr.havelsan.ueransim.nas.impl.messages.ServiceRequest;
import tr.havelsan.ueransim.ngap.ngap_ies.*;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

import static tr.havelsan.ueransim.ngap.ngap_ies.RRCEstablishmentCause.ASN_mo_Signalling;
import static tr.havelsan.ueransim.ngap2.NgapCriticality.IGNORE;
import static tr.havelsan.ueransim.ngap2.NgapCriticality.REJECT;
import static tr.havelsan.ueransim.ngap2.NgapPduDescription.INITIATING_MESSAGE;

public class ServiceRequestFlow extends BaseFlow {

    private final ServiceRequestFlowInput input;

    public ServiceRequestFlow(SimulationContext simContext, ServiceRequestFlowInput input) {
        super(simContext);
        this.input = input;
    }

    @Override
    public State main(IncomingMessage message) {
        return sendMessage();
    }

    private State sendMessage() {
        var serviceRequest = new ServiceRequest();
        serviceRequest.ngKSI = new IENasKeySetIdentifier(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, input.ngKSI);
        serviceRequest.serviceType = new IEServiceType(EServiceType.SIGNALLING);
        serviceRequest.tmsi = input.tmsi;

        var fivegTmsi = new FiveG_S_TMSI();
        fivegTmsi.aMFPointer = new AMFPointer(new byte[]{(byte) input.tmsi.amfPointer.intValue()}, 6);
        fivegTmsi.aMFSetID = new AMFSetID(input.tmsi.amfSetId.toByteArray(), 10);
        fivegTmsi.fiveG_TMSI = new FiveG_TMSI(input.tmsi.tmsi.toByteArray());

        sendNgap(new NgapBuilder()
                .withDescription(INITIATING_MESSAGE)
                .withProcedure(NgapProcedure.InitialUEMessage, IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, REJECT)
                .addNasPdu(serviceRequest, REJECT)
                .addUserLocationInformationNR(input.userLocationInformationNr, REJECT)
                .addProtocolIE(new RRCEstablishmentCause(ASN_mo_Signalling), IGNORE)
                .addProtocolIE(fivegTmsi, REJECT)
                .build());

        return this::waitForDownlinkNasTransport;
    }

    private State waitForDownlinkNasTransport(IncomingMessage message) {
        var downlinkNasTransport = message.getNgapMessage(DownlinkNASTransport.class);
        if (downlinkNasTransport == null) {
            Console.println(Color.YELLOW, "unexpected message ignored");
            return this::waitForDownlinkNasTransport;
        }

        logFlowComplete();
        return abortReceiver();
    }
}
