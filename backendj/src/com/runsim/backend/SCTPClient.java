package com.runsim.backend;

import com.sun.nio.sctp.*;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class SCTPClient {

    public void send(String hostname, int port, int protocolId, int streamNumber, byte[] data) throws Exception {
        InetSocketAddress serverAddress = new InetSocketAddress(hostname, port);
        SctpChannel channel = SctpChannel.open(serverAddress, 0, 0);
        AssociationHandler associationHandler = new AssociationHandler();

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

            byte[] receivedBytes = new byte[messageInfo.bytes()];
            for (int i = 0; i < receivedBytes.length; i++) {
                receivedBytes[i] = incomingBuffer.get(i);
            }

            handleMessage(channel, receivedBytes);
        }

        channel.close();
    }

    private void handleMessage(SctpChannel channel, byte[] data) throws Exception {

    }

    private static class AssociationHandler extends AbstractNotificationHandler<PrintStream> {

        @Override
        public HandlerResult handleNotification(AssociationChangeNotification notification, PrintStream attachment) {
            if (notification.event() == AssociationChangeNotification.AssocChangeEvent.COMM_UP) {
                int outbound = notification.association().maxOutboundStreams();
                int inbound = notification.association().maxInboundStreams();
                attachment.printf(
                        "New association setup with %d outbound streams" + ", and %d inbound streams.\n",
                        outbound, inbound
                );
            }
            return HandlerResult.CONTINUE;
        }

        @Override
        public HandlerResult handleNotification(ShutdownNotification notification, PrintStream attachment) {
            attachment.print("The association has been shutdown.\n");
            return HandlerResult.RETURN;
        }
    }
}
