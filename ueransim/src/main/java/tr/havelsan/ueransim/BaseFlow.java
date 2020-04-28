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

    private static void logReceivedMessage(NGAP_PDU ngapPdu) {
        Console.printDiv();
        Console.println(Color.BLUE, "Received NGAP PDU:");
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(ngapPdu)));
    }

    private static void logNasMessageWillSend(NasMessage nasMessage) {
        Console.printDiv();
        Console.println(Color.BLUE, nasMessage.getClass().getSimpleName() + " will be sent");
        Console.println(Color.BLUE, "While NAS message is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(nasMessage));
    }

    private static void logNgapMessageWillSend(NGAP_PDU ngapPdu) {
        Console.printDiv();
        Console.println(Color.BLUE, "Following NGAP message will be sent:");
        Console.println(Color.WHITE_BRIGHT, Utils.xmlToJson(Ngap.xerEncode(ngapPdu)));
    }

    private static void logMessageSent() {
        Console.println(Color.BLUE, "Message sent");
    }

    protected void logFlowComplete() {
        Console.println(Color.GREEN_BOLD, "%s completed", getClass().getSimpleName());
    }

    //======================================================================================================
    //                                          DATA SENDING
    //======================================================================================================

    protected final void sendNgap(byte[] data) {
        try {
            simContext.getSctpClient().send(this.streamNumber, data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected final void sendNgap(NGAP_PDU pdu) {
        logNgapMessageWillSend(pdu);
        sendNgap(Ngap.perEncode(pdu));
        logMessageSent();
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
        var ngapIn = Ngap.perDecode(NGAP_PDU.class, receivedBytes);
        logReceivedMessage(ngapIn);

        this.currentState = this.currentState.accept(ngapIn);
    }

    //======================================================================================================
    //                                             STATES
    //======================================================================================================

    protected final State sinkState(NGAP_PDU ngapIn) {
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

    public abstract State main(NGAP_PDU ngapIn) throws Exception;

    //======================================================================================================
    //                                           INTERFACES
    //======================================================================================================

    @FunctionalInterface
    public interface State {
        State accept(NGAP_PDU message);
    }
}
