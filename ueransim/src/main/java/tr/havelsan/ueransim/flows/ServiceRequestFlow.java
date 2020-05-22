package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.*;
import tr.havelsan.ueransim.configs.ServiceRequestFlowConfig;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.nas.impl.ies.IEServiceType;
import tr.havelsan.ueransim.nas.impl.ies.IEServiceType.EServiceType;
import tr.havelsan.ueransim.nas.impl.messages.ServiceRequest;
import tr.havelsan.ueransim.ngap.ngap_ies.*;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

import static tr.havelsan.ueransim.ngap.ngap_ies.RRCEstablishmentCause.ASN_mo_Signalling;
import static tr.havelsan.ueransim.ngap2.NgapCriticality.IGNORE;
import static tr.havelsan.ueransim.ngap2.NgapCriticality.REJECT;

public class ServiceRequestFlow extends BaseFlow {

    private final ServiceRequestFlowConfig config;

    public ServiceRequestFlow(SimulationContext simContext, ServiceRequestFlowConfig config) {
        super(simContext);
        this.config = config;
    }

    @Override
    public State main(IncomingMessage message) {
        return sendMessage();
    }

    private State sendMessage() {
        var serviceRequest = new ServiceRequest();
        serviceRequest.ngKSI = new IENasKeySetIdentifier(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, config.ngKSI);
        serviceRequest.serviceType = new IEServiceType(EServiceType.SIGNALLING);
        serviceRequest.tmsi = config.tmsi;

        var fivegTmsi = new FiveG_S_TMSI();
        fivegTmsi.aMFPointer = new AMFPointer(new byte[]{(byte) config.tmsi.amfPointer.intValue()}, 6);
        fivegTmsi.aMFSetID = new AMFSetID(config.tmsi.amfSetId.toByteArray(), 10);
        fivegTmsi.fiveG_TMSI = new FiveG_TMSI(config.tmsi.tmsi.toByteArray());

        send(new SendingMessage(new NgapBuilder(NgapProcedure.InitialUEMessage, IGNORE)
                .addProtocolIE(new RRCEstablishmentCause(ASN_mo_Signalling), IGNORE)
                .addProtocolIE(fivegTmsi, REJECT), serviceRequest));

        return this::waitForDownlinkNasTransport;
    }

    private State waitForDownlinkNasTransport(IncomingMessage message) {
        var downlinkNasTransport = message.getNgapMessage(DownlinkNASTransport.class);
        if (downlinkNasTransport == null) {
            FlowLogging.logUnhandledMessage(message, DownlinkNASTransport.class);
            return this::waitForDownlinkNasTransport;
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
