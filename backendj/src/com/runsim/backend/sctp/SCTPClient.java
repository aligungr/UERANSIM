package com.runsim.backend.sctp;

import com.sun.nio.sctp.*;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class SCTPClient {

    private final String hostname;
    private final int port;
    private final int protocolId;
    private final SctpChannel channel;
    private final AssociationHandler associationHandler;

    public SCTPClient(String hostname, int port, int protocolId) throws Exception {
        this.hostname = hostname;
        this.port = port;
        this.protocolId = protocolId;

        InetSocketAddress serverAddress = new InetSocketAddress(hostname, port);
        this.channel = SctpChannel.open(serverAddress, 0, 0);
        associationHandler = new AssociationHandler();
    }

    public void send(int streamNumber, byte[] data, Consumer<Byte[]> handler) throws Exception {
        ByteBuffer outgoingBuffer = ByteBuffer.wrap(data);
        ByteBuffer incomingBuffer = ByteBuffer.allocate(1073741824);

        MessageInfo outgoingMessage = MessageInfo.createOutgoing(null, streamNumber);
        outgoingMessage.payloadProtocolID(protocolId);

        channel.send(outgoingBuffer, outgoingMessage);

        MessageInfo messageInfo;
        while (true) {
            messageInfo = channel.receive(incomingBuffer, System.out, associationHandler);
            if (messageInfo == null || messageInfo.bytes() == -1) {
                break;
            }

            Byte[] receivedBytes = new Byte[messageInfo.bytes()];
            for (int i = 0; i < receivedBytes.length; i++) {
                receivedBytes[i] = incomingBuffer.get(i);
            }

            handler.accept(receivedBytes);
        }

        channel.close();
    }
}
