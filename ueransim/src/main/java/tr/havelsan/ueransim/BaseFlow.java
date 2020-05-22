package tr.havelsan.ueransim;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;
import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.core.IMessageListener;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;

public abstract class BaseFlow implements IMessageListener {
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

    @Deprecated
    public final void send(SendingMessage sendingMessage) {
        Messaging.send(ctx, sendingMessage);
    }

    private void receive(NGAP_PDU ngapPdu) {
        var incomingMessage = Messaging.handleIncomingMessage(ctx, ngapPdu);
        FlowLogging.logReceivedMessage(incomingMessage);
        try {
            this.currentState = this.currentState.accept(incomingMessage);
        } catch (FlowFailedException exception) {
            this.currentState = flowFailed(exception.getMessage());
        }
        ctx.dispatchMessageReceive(incomingMessage);
    }

    //======================================================================================================
    //                                            GENERAL
    //======================================================================================================

    public final void start() throws Exception {
        if (started) throw new RuntimeException("already started");
        this.started = true;

        ctx.registerListener(this);

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

    //======================================================================================================
    //                                             STATES
    //======================================================================================================

    private State sinkState(IncomingMessage message) {
        return this::sinkState;
    }

    public final State flowComplete() {
        FlowLogging.logFlowComplete(this);
        return abortFlow();
    }

    public final State flowFailed(String errorMessage) {
        FlowLogging.logFlowFailed(this, errorMessage);
        return abortFlow();
    }

    public final State abortFlow() {
        ctx.unregisterListener();
        ctx.sctpClient.abortReceiver();
        return this::sinkState;
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
