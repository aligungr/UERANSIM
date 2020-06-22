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

package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.OutgoingMessage;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationRequest;
import tr.havelsan.ueransim.sctp.ISCTPClient;
import tr.havelsan.ueransim.structs.SmContext;
import tr.havelsan.ueransim.structs.UeConfig;
import tr.havelsan.ueransim.structs.UeData;
import tr.havelsan.ueransim.structs.UeTimers;

public class SimulationContext {
    // Connection related
    public ISCTPClient sctpClient;
    public int streamNumber;
    public String amfHost;
    public int amfPort;

    // UE related
    public UeData ueData;
    public UeConfig ueConfig;
    public NasSecurityContext currentNsc;
    public NasSecurityContext nonCurrentNsc;
    public UeTimers ueTimers;
    public RegistrationRequest registrationRequest;
    public SmContext smCtx;

    // NGAP IE related
    public Long amfUeNgapId;
    public long ranUeNgapId;

    // Message callback
    private IMessageListener messageListener;

    public SimulationContext() {
        this.messageListener = null;
        this.ueTimers = new UeTimers();
        this.smCtx = new SmContext();
    }

    // todo: use read/write lock instead of synchronized

    public synchronized void registerListener(IMessageListener listener) {
        messageListener = listener;
    }

    public synchronized void unregisterListener() {
        messageListener = null;
    }

    public synchronized void dispatchMessageReceive(IncomingMessage incomingMessage) {
        var listener = messageListener;
        if (listener != null) listener.onReceive(incomingMessage);
    }

    public synchronized void dispatchMessageSent(OutgoingMessage outgoingMessage) {
        var listener = messageListener;
        if (listener != null) listener.onSent(outgoingMessage);
    }
}
