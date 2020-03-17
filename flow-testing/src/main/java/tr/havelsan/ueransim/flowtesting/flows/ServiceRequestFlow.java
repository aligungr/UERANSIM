package tr.havelsan.ueransim.flowtesting.flows;

import static tr.havelsan.ueransim.ngap.ngap_ies.RRCEstablishmentCause.ASN_mo_Signalling;
import static tr.havelsan.ueransim.ngap2.NgapCriticality.IGNORE;
import static tr.havelsan.ueransim.ngap2.NgapCriticality.REJECT;
import static tr.havelsan.ueransim.ngap2.NgapPduDescription.INITIATING_MESSAGE;

import tr.havelsan.ueransim.flowtesting.inputs.ServiceRequestFlowInput;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IEServiceType;
import tr.havelsan.ueransim.nas.impl.ies.IEServiceType.EServiceType;
import tr.havelsan.ueransim.nas.impl.messages.ServiceRequest;
import tr.havelsan.ueransim.ngap.ngap_ies.AMFPointer;
import tr.havelsan.ueransim.ngap.ngap_ies.AMFSetID;
import tr.havelsan.ueransim.ngap.ngap_ies.FiveG_S_TMSI;
import tr.havelsan.ueransim.ngap.ngap_ies.FiveG_TMSI;
import tr.havelsan.ueransim.ngap.ngap_ies.RRCEstablishmentCause;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.sim.BaseFlow;
import tr.havelsan.ueransim.sim.Message;
import tr.havelsan.ueransim.sim.ue.FlowUtils;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class ServiceRequestFlow extends BaseFlow {

  final ServiceRequestFlowInput input;

  public ServiceRequestFlow(SCTPClient sctpClient, ServiceRequestFlowInput input) {
    super(sctpClient);
    this.input = input;
  }

  @Override
  public State main(Message message) {

    return sendInitialUeMessage();
  }

  private State sendInitialUeMessage() {
    var serviceRequest = new ServiceRequest();
    serviceRequest.ngKSI = new IENasKeySetIdentifier(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, input.ngKSI);
    serviceRequest.serviceType = new IEServiceType(EServiceType.SIGNALLING);
    serviceRequest.tmsi = input.tmsi;

    var fivegTmsi = new FiveG_S_TMSI();
    fivegTmsi.aMFPointer = new AMFPointer(new byte[]{(byte) input.tmsi.amfPointer.intValue()}, 6);
    fivegTmsi.aMFSetID = new AMFSetID(input.tmsi.amfSetId.toByteArray(), 10);
    fivegTmsi.fiveG_TMSI = new FiveG_TMSI(input.tmsi.tmsi.toByteArray());

    var ngSetupRequest = new NgapBuilder()
        .withDescription(INITIATING_MESSAGE)
        .withProcedure(NgapProcedure.InitialUEMessage, IGNORE)
        .addRanUeNgapId(input.ranUeNgapId, REJECT)
        .addNasPdu(serviceRequest, REJECT)
        .addUserLocationInformationNR(input.userLocationInformationNr, REJECT)
        .addProtocolIE(new RRCEstablishmentCause(ASN_mo_Signalling), IGNORE)
        .addProtocolIE(fivegTmsi, REJECT)
        .build();
    sendPDU(ngSetupRequest);

    return this::waitForDownLinkNasTransport;
  }

  private State waitForDownLinkNasTransport(Message message) {
    var pdu = message.getAsPDU();
    FlowUtils.logReceivedMessage(pdu);

    var value = ((InitiatingMessage) pdu.getValue()).value.getDecodedValue();

    if (value instanceof DownlinkNASTransport) {
      Console.println(
          Color.GREEN_BOLD,
          "DownlinkNASTransport arrived, Service Request Completed.");
      return abortReceiver();
    }
    return this::waitForDownLinkNasTransport;
  }
}
