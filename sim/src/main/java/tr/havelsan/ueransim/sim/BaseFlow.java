package tr.havelsan.ueransim.sim;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;
import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.sim.contexts.SimulationContext;
import tr.havelsan.ueransim.sim.ue.FlowUtils;
import tr.havelsan.ueransim.utils.Utils;

public abstract class BaseFlow {
    private final SimulationContext simContext;
    private boolean started;
    private int streamNumber;
    private State currentState;

    public BaseFlow(SimulationContext simContext) {
        this.simContext = simContext;
    }

    protected final void sendData(byte[] data) {
        try {
            simContext.getSctpClient().send(this.streamNumber, data);
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
        this.simContext.getSctpClient().receiverLoop(this::handleSCTPMessage);
    }

    private void handleSCTPMessage(byte[] receivedBytes, MessageInfo messageInfo, SctpChannel channel) {
        var message = new Message(receivedBytes, messageInfo.streamNumber());
        this.currentState = this.currentState.accept(message);
    }

    public final State closeConnection() {
        simContext.getSctpClient().close();
        return this::sinkState;
    }

    public final State abortReceiver() {
        simContext.getSctpClient().abortReceiver();
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
