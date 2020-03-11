package tr.havelsan.ueransim.flowtesting.flows;

import tr.havelsan.ueransim.flowtesting.inputs.ServiceRequestFlowInput;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.sim.BaseFlow;
import tr.havelsan.ueransim.sim.Message;
import tr.havelsan.ueransim.sim.ue.FlowUtils;
import tr.havelsan.ueransim.sim.ue.UeUtils;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class ServiceRequestFlow extends BaseFlow {

  ServiceRequestFlowInput input;

  public ServiceRequestFlow(SCTPClient sctpClient, ServiceRequestFlowInput input) {
    super(sctpClient);
    this.input = input;
  }

  @Override
  public State main(Message message) {

    return sendInitialUeMessage();
  }


  private State sendInitialUeMessage() {

    var ngSetupRequest = UeUtils
        .createInitialUeMessageServiceRequest(input.ranUeNgapId, input.userLocationInformationNr,input.amfPointer,input.amfSetId,input.fiveg_tmsi);
    sendNgapMessage(ngSetupRequest);

    return this::waitForDownLinkNasTransport;
  }

  private State waitForDownLinkNasTransport(Message message) {
    var pdu = message.getAsPDU();
    FlowUtils.logReceivedMessage(pdu);

    var value = ((InitiatingMessage) pdu.getValue()).value.getDecodedValue();

    if (value instanceof DownlinkNASTransport) {
      Console.println(
          Color.GREEN,
          "DownlinkNASTransport arrived, Service Request Completed.");
      return abortReceiver();
    }
    return this::waitForDownLinkNasTransport;
  }

  public void sendNgapMessage(NGAP_PDU ngapPdu) {
    FlowUtils.logNgapMessageWillSend(ngapPdu);
    sendPDU(ngapPdu);
    FlowUtils.logMessageSent();
  }

}
