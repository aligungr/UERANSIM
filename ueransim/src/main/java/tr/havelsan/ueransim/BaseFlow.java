package tr.havelsan.ueransim;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.core.Constants;
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

    private static void logReceivedMessage(IncomingMessage incomingMessage) {
        Console.printDiv();
        Console.println(Color.BLUE, "Received NGAP:");
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(incomingMessage.ngapPdu)));
        if (incomingMessage.nasMessage != null) {
            Console.println(Color.BLUE, "Received NAS:");
            Console.println(Color.WHITE_BRIGHT, Json.toJson(incomingMessage.nasMessage));
        }
    }

    private static void logSentMessage(OutgoingMessage message) {
        Console.printDiv();
        Console.println(Color.BLUE, "Sent NGAP:");
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(message.ngapPdu)));

        // todo log nas also
    }

    private void logFlowComplete() {
        Console.println(Color.GREEN_BOLD, "%s completed", getClass().getSimpleName());
    }

    //======================================================================================================
    //                                          DATA SENDING
    //======================================================================================================

    private void sendData(byte[] data) {
        try {
            simContext.getSctpClient().send(this.streamNumber, data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected final void sendNgap(NGAP_PDU pdu) {
        sendData(Ngap.perEncode(pdu));
        logSentMessage(new OutgoingMessage(pdu));
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
        var ngapPdu = Ngap.perDecode(NGAP_PDU.class, receivedBytes);
        var ngapMessage = URSimUtils.extractNgapMessage(ngapPdu);
        var nasMessage = URSimUtils.extractNasMessage(ngapPdu);

        // todo: decrypt nas if needed

        var incomingMessage = new IncomingMessage(ngapPdu, ngapMessage, nasMessage);


        logReceivedMessage(incomingMessage);
        this.currentState = this.currentState.accept(incomingMessage);
    }

    //======================================================================================================
    //                                             STATES
    //======================================================================================================

    protected final State sinkState(IncomingMessage message) {
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

    public final State flowComplete() {
        logFlowComplete();
        return abortReceiver();
    }

    public abstract State main(IncomingMessage message) throws Exception;

    //======================================================================================================
    //                                           INTERFACES
    //======================================================================================================

    @FunctionalInterface
    public interface State {
        State accept(IncomingMessage message);
    }
}
