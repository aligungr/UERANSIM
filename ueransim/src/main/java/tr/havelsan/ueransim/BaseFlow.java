package tr.havelsan.ueransim;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;
import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;

public abstract class BaseFlow {
    protected final SimulationContext ctx;
    private boolean started;
    private State currentState;

    //======================================================================================================
    //                                          CONSTRUCTORS
    //======================================================================================================

    public BaseFlow(SimulationContext simContext) {
        this.ctx = simContext;
    }

    //======================================================================================================
    //                                           MESSAGING
    //======================================================================================================

    protected final void send(SendingMessage sendingMessage) {
        var outgoing = Messaging.handleOutgoingMessage(ctx, sendingMessage);
        sendSctpData(Ngap.perEncode(outgoing.ngapPdu));
        FlowLogging.logSentMessage(outgoing);
    }

    private void receive(NGAP_PDU ngapPdu) {
        var incomingMessage = Messaging.handleIncomingMessage(ctx, ngapPdu);
        FlowLogging.logReceivedMessage(incomingMessage);
        try {
            this.currentState = this.currentState.accept(incomingMessage);
        } catch (FlowFailedException exception) {
            this.currentState = flowFailed(exception.getMessage());
        }
    }

    //======================================================================================================
    //                                            GENERAL
    //======================================================================================================

    public final void start() throws Exception {
        if (started) throw new RuntimeException("already started");
        this.started = true;

        boolean mainStepFailed = false;
        try {
            this.currentState = main(null);
        } catch (FlowFailedException exception) {
            this.currentState = flowFailed(exception.getMessage());
            mainStepFailed = true;
        }

        if (!mainStepFailed) {
            this.ctx.sctpClient.receiverLoop(this::receiveSctpData);
        }
    }

    private void receiveSctpData(byte[] receivedBytes, MessageInfo messageInfo, SctpChannel channel) {
        receive(Ngap.perDecode(NGAP_PDU.class, receivedBytes));
    }

    private void sendSctpData(byte[] data) {
        try {
            ctx.sctpClient.send(ctx.streamNumber, data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //======================================================================================================
    //                                             STATES
    //======================================================================================================

    protected final State sinkState(IncomingMessage message) {
        return this::sinkState;
    }

    public final State closeConnection() {
        ctx.sctpClient.close();
        return this::sinkState;
    }

    public final State abortReceiver() {
        ctx.sctpClient.abortReceiver();
        return this::sinkState;
    }

    public final State flowComplete() {
        FlowLogging.logFlowComplete(this);
        return abortReceiver();
    }

    public final State flowFailed(String errorMessage) {
        FlowLogging.logFlowFailed(this, errorMessage);
        return abortReceiver();
    }

    public final State flowFailed() {
        return flowFailed(null);
    }

    public abstract State main(IncomingMessage message) throws Exception;

    //======================================================================================================
    //                                           OTHERS
    //======================================================================================================

    @FunctionalInterface
    public interface State {
        State accept(IncomingMessage message);
    }

    protected static class FlowFailedException extends RuntimeException {
        public FlowFailedException() {
        }

        public FlowFailedException(String message) {
            super(message);
        }
    }
}
