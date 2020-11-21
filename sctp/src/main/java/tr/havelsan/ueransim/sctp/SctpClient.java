/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.sctp;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class SctpClient implements ISctpClient {
    private static final int RECEIVER_BUFFER_SIZE = 65536;

    private final String localHost;
    private final String remoteHost;
    private final int remotePort;
    private final int protocolId;
    private final SctpNotificationHandler associationHandler;

    private SctpChannel channel;
    private boolean receiving;

    public SctpClient(String localHost, String remoteHost, int remotePort, int protocolId, ISctpAssociationHandler sctpAssociationHandler) {
        this.localHost = localHost;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.protocolId = protocolId;
        this.associationHandler = new SctpNotificationHandler(sctpAssociationHandler);
    }

    @Override
    public void start() throws Exception {
        if (this.channel != null) throw new RuntimeException("start was already called");

        Log.info(Tag.CONN, "Trying to establish SCTP connection... (%s:%s)", remoteHost, remotePort);

        this.channel = SctpChannel.open();
        this.channel.bind(new InetSocketAddress(localHost, 0));
        this.channel.connect(new InetSocketAddress(remoteHost, remotePort), 0, 0);
        this.receiving = true;

        Log.info(Tag.CONN, "SCTP connection established");
    }

    @Override
    public void send(int streamNumber, byte[] data) {
        ByteBuffer outgoingBuffer = ByteBuffer.wrap(data);
        MessageInfo outgoingMessage = MessageInfo.createOutgoing(null, streamNumber);
        outgoingMessage.payloadProtocolID(protocolId);
        try {
            channel.send(outgoingBuffer, outgoingMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void receiverLoop(ISctpHandler handler) throws Exception {
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
            handler.handleSCTPMessage(receivedBytes, messageInfo.streamNumber());
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
        return channel != null && channel.isOpen();
    }
}
