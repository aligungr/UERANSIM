package com.runsim.backend;

import com.sun.nio.sctp.*;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class SCTPTestServer {

    public void start(int port) throws Exception {
        InetSocketAddress serverAddress = new InetSocketAddress(port);
        SctpServerChannel ssc = SctpServerChannel.open();
        ssc.bind(serverAddress);
        System.out.println("server started listening on port " + port);

        ByteBuffer incomingBuffer = ByteBuffer.allocateDirect(1073741824);
        while (true) {
            SctpChannel sc = ssc.accept();

            AssociationHandler assocHandler = new AssociationHandler();
            MessageInfo inMessageInfo;
            while (true) {
                inMessageInfo = sc.receive(incomingBuffer, System.out, assocHandler);
                if (inMessageInfo == null || inMessageInfo.bytes() == -1) {
                    break;
                }

                byte[] receivedBytes = new byte[inMessageInfo.bytes()];
                for (int i = 0; i < receivedBytes.length; i++) {
                    receivedBytes[i] = incomingBuffer.get(i);
                }

                handleMessage(sc, receivedBytes);
            }
            sc.close();
        }
    }

    private void handleMessage(SctpChannel channel, byte[] data) throws Exception {
        MessageInfo msg = MessageInfo.createOutgoing(null, 0);
        msg.payloadProtocolID(60);
        channel.send(ByteBuffer.wrap(new byte[] { 1, 2, 3, 4 }), msg);
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
