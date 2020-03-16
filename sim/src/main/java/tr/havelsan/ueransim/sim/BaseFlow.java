package tr.havelsan.ueransim.sim;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;
import fr.marben.asnsdk.japi.spe.Value;
import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.sim.ue.FlowUtils;
import tr.havelsan.ueransim.utils.Utils;

public abstract class BaseFlow {
    private SCTPClient sctpClient;
    private boolean started;
    private int streamNumber;
    private State currentState;

    public BaseFlow(SCTPClient sctpClient) {
        this.sctpClient = sctpClient;
    }

    protected final void sendData(byte[] data) {
        try {
            sctpClient.send(this.streamNumber, data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected final void sendData(int... data) {
        byte[] bytes = new byte[data.length];
        for (int i = 0; i < data.length; i++)
            bytes[i] = (byte) data[i];
        sendData(bytes);
    }

    protected final void sendBase16(String hexString) {
        sendData(Utils.hexStringToByteArray(hexString));
    }

    protected final void sendPDU(NGAP_PDU pdu) {
        FlowUtils.logNgapMessageWillSend(pdu);
        sendData(Ngap.perEncode(pdu));
        FlowUtils.logMessageSent();
    }

    protected final void sendPDU(String xml) {
        sendPDU(Ngap.xerDecode(NGAP_PDU.class, xml));
    }

    public final void start() throws Exception {
        if (started) throw new RuntimeException("already started");

        this.started = true;
        this.streamNumber = Constants.DEFAULT_STREAM_NUMBER;
        this.currentState = main(null);
        this.sctpClient.receiverLoop(this::handleSCTPMessage);
    }

    private void handleSCTPMessage(byte[] receivedBytes, MessageInfo messageInfo, SctpChannel channel) {
        var message = new Message(receivedBytes, messageInfo.streamNumber());
        this.currentState = this.currentState.accept(message);
    }

    public final State closeConnection() {
        sctpClient.close();
        return this::sinkState;
    }

    public final State abortReceiver() {
        sctpClient.abortReceiver();
        return this::sinkState;
    }

    protected final State sinkState(Message message) {
        return this::sinkState;
    }

    public abstract State main(Message message) throws Exception;

    @FunctionalInterface
    public interface State {
        State accept(Message message);
    }
}
