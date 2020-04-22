package tr.havelsan.ueransim;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Utils;

public abstract class BaseFlow {
    private final SimulationContext simContext;
    private boolean started;
    private int streamNumber;
    private State currentState;

    //======================================================================================================
    //                                          CONSTRUCTORS
    //======================================================================================================

    public BaseFlow(SimulationContext simContext) {
        this.simContext = simContext;
    }

    //======================================================================================================
    //                                            LOGGING
    //======================================================================================================

    public static void logReceivedMessage(NGAP_PDU ngapPdu) {
        Console.printDiv();
        Console.println(Color.BLUE, "Received NGAP PDU:");
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(ngapPdu)));
    }

    public static void logNasMessageWillSend(NasMessage nasMessage) {
        Console.printDiv();
        Console.println(Color.BLUE, nasMessage.getClass().getSimpleName() + " will be sent");
        Console.println(Color.BLUE, "While NAS message is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(nasMessage));
    }

    public static void logNgapMessageWillSend(NGAP_PDU ngapPdu) {
        Console.printDiv();
        Console.println(Color.BLUE, "Following NGAP message will be sent:");
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(ngapPdu)));
    }

    public static void logMessageSent() {
        Console.println(Color.BLUE, "Message sent");
    }

    //======================================================================================================
    //                                          DATA SENDING
    //======================================================================================================

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
        logNgapMessageWillSend(pdu);
        sendData(Ngap.perEncode(pdu));
        logMessageSent();
    }

    protected final void sendPDU(String xml) {
        sendPDU(Ngap.xerDecode(NGAP_PDU.class, xml));
    }

    //======================================================================================================
    //                                            GENERAL
    //======================================================================================================

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

    //======================================================================================================
    //                                             STATES
    //======================================================================================================

    protected final State sinkState(Message message) {
        return this::sinkState;
    }

    public final State closeConnection() {
        simContext.getSctpClient().close();
        return this::sinkState;
    }

    public final State abortReceiver() {
        simContext.getSctpClient().abortReceiver();
        return this::sinkState;
    }

    public abstract State main(Message message) throws Exception;

    //======================================================================================================
    //                                           INTERFACES
    //======================================================================================================

    @FunctionalInterface
    public interface State {
        State accept(Message message);
    }
}
