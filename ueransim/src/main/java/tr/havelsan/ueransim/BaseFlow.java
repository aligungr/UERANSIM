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
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;
import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.core.IMessageListener;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.utils.FlowLogging;

public abstract class BaseFlow implements IMessageListener {
    protected final SimulationContext ctx;

    public BaseFlow(SimulationContext simContext) {
        this.ctx = simContext;
    }

    public final void start() throws Exception {
        ctx.registerListener(this);
        main();
        this.ctx.sctpClient.receiverLoop(this::receiveSctpData);
    }

    private void receiveSctpData(byte[] receivedBytes, MessageInfo messageInfo, SctpChannel channel) {
        var ngapPdu = Ngap.perDecode(NGAP_PDU.class, receivedBytes);

        var incomingMessage = Messaging.handleIncomingMessage(ctx, ngapPdu);
        ctx.dispatchMessageReceive(incomingMessage);
    }

    public final void flowComplete() {
        FlowLogging.logFlowComplete(this);
        ctx.unregisterListener();
        ctx.sctpClient.abortReceiver();
    }

    public abstract void main();
}
