/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.sctp;

import com.sun.nio.sctp.AbstractNotificationHandler;
import com.sun.nio.sctp.AssociationChangeNotification;
import com.sun.nio.sctp.HandlerResult;
import com.sun.nio.sctp.ShutdownNotification;

import java.io.PrintStream;

class SctpNotificationHandler extends AbstractNotificationHandler<PrintStream> {

    private final ISctpAssociationHandler sctpAssociationHandler;

    public SctpNotificationHandler(ISctpAssociationHandler sctpAssociationHandler) {
        this.sctpAssociationHandler = sctpAssociationHandler;
    }

    @Override
    public HandlerResult handleNotification(AssociationChangeNotification notification, PrintStream attachment) {
        if (notification.event() == AssociationChangeNotification.AssocChangeEvent.COMM_UP) {
            sctpAssociationHandler.onSetup(new SctpAssociation(notification.association().associationID(),
                    notification.association().maxInboundStreams(),
                    notification.association().maxOutboundStreams()));
            return HandlerResult.CONTINUE;
        }
        throw new RuntimeException("SCTP association failure: " + notification.event());
    }

    @Override
    public HandlerResult handleNotification(ShutdownNotification notification, PrintStream attachment) {
        sctpAssociationHandler.onShutdown();
        return HandlerResult.RETURN;
    }
}