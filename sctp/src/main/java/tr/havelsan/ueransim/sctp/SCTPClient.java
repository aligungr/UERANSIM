package tr.havelsan.ueransim.sctp;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class SCTPClient implements ISCTPClient {
    private static final int RECEIVER_BUFFER_SIZE = 1073741824;

    private final String host;
    private final int port;
    private final int protocolId;

    private SctpChannel channel;
    private AssociationHandler associationHandler;
    private boolean receiving;

    public SCTPClient(String host, int port, int protocolId) {
        this.host = host;
        this.port = port;
        this.protocolId = protocolId;
    }

    @Override
    public void start() throws Exception {
        if (this.channel != null) throw new RuntimeException("start was already called");
        var serverAddress = new InetSocketAddress(host, port);
        this.channel = SctpChannel.open(serverAddress, 0, 0);
        this.associationHandler = new AssociationHandler();
        this.receiving = true;
    }

    @Override
    public void send(int streamNumber, byte[] data) throws Exception {
        ByteBuffer outgoingBuffer = ByteBuffer.wrap(data);
        MessageInfo outgoingMessage = MessageInfo.createOutgoing(null, streamNumber);
        outgoingMessage.payloadProtocolID(protocolId);
        channel.send(outgoingBuffer, outgoingMessage);
    }

    @Override
    public void receiverLoop(ISCTPHandler handler) throws Exception {
        receiving = true;

        MessageInfo messageInfo;
        while (receiving && channel.isOpen()) {
            ByteBuffer incomingBuffer = ByteBuffer.allocate(RECEIVER_BUFFER_SIZE);
            messageInfo = channel.receive(incomingBuffer, System.out, associationHandler);
            if (messageInfo == null || messageInfo.bytes() == -1) break;

            byte[] receivedBytes = new byte[messageInfo.bytes()];
            for (int i = 0; i < receivedBytes.length; i++) {
                receivedBytes[i] = incomingBuffer.get(i);
            }
            handler.handleSCTPMessage(receivedBytes, messageInfo, channel);
        }
    }

    @Override
    public void close() {
        try {
            channel.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void abortReceiver() {
        receiving = false;
    }

    @Override
    public boolean isOpen() {
        return channel.isOpen();
    }
}
