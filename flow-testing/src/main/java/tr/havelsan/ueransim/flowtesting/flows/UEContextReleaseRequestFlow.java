package tr.havelsan.ueransim.flowtesting.flows;

import fr.marben.asnsdk.japi.InvalidStructureException;
import tr.havelsan.ueransim.flowtesting.inputs.UEContextReleaseRequestInput;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.sim.BaseFlow;
import tr.havelsan.ueransim.sim.Message;
import tr.havelsan.ueransim.sim.ue.FlowUtils;
import tr.havelsan.ueransim.sim.ue.UeUtils;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class UEContextReleaseRequestFlow extends BaseFlow {

  UEContextReleaseRequestInput input;

  public UEContextReleaseRequestFlow(SCTPClient sctpClient, UEContextReleaseRequestInput input) {
    super(sctpClient);
    this.input = input;
  }

  @Override
  public State main(Message message) throws Exception {

    return sendNgSetupRequest();
  }

  private State sendNgSetupRequest() throws InvalidStructureException {
    var ngSetupRequest = UeUtils.createUEContextRelease(input.ranUeNgapId, input.amfUeNgapId);
    sendNgapMessage(ngSetupRequest);
    return this::waitPduSessionReleaseCommand;
  }

  public void sendNgapMessage(NGAP_PDU ngapPdu) {
    FlowUtils.logNgapMessageWillSend(ngapPdu);
    sendPDU(ngapPdu);
    FlowUtils.logMessageSent();
  }

  private State waitPduSessionReleaseCommand(Message message) {

/*
    var pdu = message.getAsPDU();
    FlowUtils.logReceivedMessage(pdu);

    var value = ((InitiatingMessage) pdu.getValue()).value.getDecodedValue();

    if (value instanceof UEContextReleaseCommand) {
      Console.println(
          Color.BLUE,
          "UEContextReleaseCommand arrived, UEContextReleaseComplete will return"); //TODO Buraya tekrardan bakılacak
      return ueContextReleaseComplete();
    }
*/

    ueContextReleaseComplete();

    return this::waitPduSessionReleaseCommand;
  }

  private State ueContextReleaseComplete() {

    var ngSetupRequest = UeUtils.ueContextReleaseComplete(input.ranUeNgapId, input.amfUeNgapId);

    sendNgapMessage(ngSetupRequest);

    Console.println(
        Color.GREEN,
        "UEContextRelease completed");

    return abortReceiver();
  }


}
