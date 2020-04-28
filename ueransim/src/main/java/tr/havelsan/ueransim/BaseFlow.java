package tr.havelsan.ueransim;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapInternal;
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

        if (message.plainNas != null) {
            Console.println(Color.BLUE, "Sent Plain NAS: %s", message.plainNas.getClass().getSimpleName());
            Console.println(Color.WHITE_BRIGHT, Json.toJson(message.plainNas));
        }
        if (message.securedNas != null && message.plainNas != message.securedNas) {
            Console.println(Color.BLUE, "Sent Secured NAS: %s", message.securedNas.getClass().getSimpleName());
            Console.println(Color.WHITE_BRIGHT, Json.toJson(message.securedNas));
        }
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

    //======================================================================================================
    //                                           MESSAGING
    //======================================================================================================

    protected final void send(NgapBuilder ngapBuilder, NasMessage nasMessage) {
        // Adding NAS PDU (if any)
        NasMessage securedNas = encryptNasMessage(nasMessage);
        if (securedNas != null) {
            // NOTE: criticality is hardcoded here, it may be changed
            ngapBuilder.addNasPdu(securedNas, NgapCriticality.REJECT);
        }

        // Adding AMF-UE-NGAP-ID (if any)
        // TODO:
        // This protocol ie should be included in all UE associated signalling.
        // AMF-UE-NGAP-ID may be ignored for non UE associated messages.
        // But currently this ie is added to all messages (if there is an AMF-UE-NGAP-ID in the context).
        long amfUeNgapId = simContext.getAmfUeNgapId();
        if (amfUeNgapId != 0) {
            // NOTE: criticality is hardcoded here, it may be changed
            ngapBuilder.addAmfUeNgapId(amfUeNgapId, NgapCriticality.IGNORE);
        }

        var ngapPdu = ngapBuilder.build();
        sendData(Ngap.perEncode(ngapPdu));

        var outgoing = new OutgoingMessage(ngapPdu, nasMessage, securedNas);
        logSentMessage(outgoing);
    }

    private void receive(NGAP_PDU ngapPdu) {
        var ngapMessage = NgapInternal.extractNgapMessage(ngapPdu);
        var nasMessage = URSimUtils.extractNasMessage(ngapPdu);
        var decryptedNasMessage = decryptNasMessage(nasMessage);
        var incomingMessage = new IncomingMessage(ngapPdu, ngapMessage, decryptedNasMessage);

        // check for AMF-UE-NGAP-ID
        {
            var ieAmfUeNgapId = NgapInternal.extractProtocolIe(ngapMessage, AMF_UE_NGAP_ID.class);
            if (ieAmfUeNgapId.size() > 0) {
                var ie = ieAmfUeNgapId.get(ieAmfUeNgapId.size() - 1);
                simContext.setAmfUeNgapId(ie.value);
            }
        }

        logReceivedMessage(incomingMessage);
        this.currentState = this.currentState.accept(incomingMessage);
    }

    //======================================================================================================
    //                                            SECURITY
    //======================================================================================================

    private NasMessage encryptNasMessage(NasMessage nasMessage) {
        if (nasMessage == null) {
            return null;
        }

        // todo: encrypt nasMessage if needed
        return nasMessage;
    }

    private NasMessage decryptNasMessage(NasMessage nasMessage) {
        if (nasMessage == null) {
            return null;
        }

        // todo: decrypt nasMessage if needed
        return nasMessage;
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
        receive(ngapPdu);
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
