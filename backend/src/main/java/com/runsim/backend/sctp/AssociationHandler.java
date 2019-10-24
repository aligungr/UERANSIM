package com.runsim.backend.sctp;

import com.sun.nio.sctp.AbstractNotificationHandler;
import com.sun.nio.sctp.AssociationChangeNotification;
import com.sun.nio.sctp.HandlerResult;
import com.sun.nio.sctp.ShutdownNotification;

import java.io.PrintStream;

public class AssociationHandler extends AbstractNotificationHandler<PrintStream> {

    @Override
    public HandlerResult handleNotification(AssociationChangeNotification notification, PrintStream attachment) {
        /*if (notification.event() == AssociationChangeNotification.AssocChangeEvent.COMM_UP) {
            int outbound = notification.association().maxOutboundStreams();
            int inbound = notification.association().maxInboundStreams();
            attachment.printf(
                    "New association setup with %d outbound streams" + ", and %d inbound streams.\n",
                    outbound, inbound
            );
        }*/
        return HandlerResult.CONTINUE;
    }

    @Override
    public HandlerResult handleNotification(ShutdownNotification notification, PrintStream attachment) {
        //attachment.print("The association has been shutdown.\n");
        return HandlerResult.RETURN;
    }
}