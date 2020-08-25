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

import java.util.ArrayDeque;
import java.util.Queue;

public class MockedSctpClient implements ISctpClient {
    private final Queue<Byte[]> queue;
    private final IMockedRemote mockedRemote;
    private final ISctpAssociationHandler sctpAssociationHandler;

    private boolean receiving;
    private boolean isOpen;

    public MockedSctpClient(IMockedRemote mockedRemote, ISctpAssociationHandler sctpAssociationHandler) {
        this.queue = new ArrayDeque<>();
        this.mockedRemote = mockedRemote;
        this.sctpAssociationHandler = sctpAssociationHandler;

        this.receiving = false;
        this.isOpen = false;
    }

    @Override
    public void start() throws Exception {
        isOpen = true;
        sctpAssociationHandler.onSetup(new SctpAssociation(1, 7, 7));
    }

    @Override
    public void send(int streamNumber, byte[] data) {
        mockedRemote.onMessage(data, queue);
    }

    @Override
    public void receiverLoop(ISctpHandler handler) throws Exception {
        receiving = true;

        while (receiving && isOpen) {
            while (true) {
                var entry = queue.poll();
                if (entry == null) break;

                byte[] response = new byte[entry.length];
                for (int i = 0; i < response.length; i++) {
                    response[i] = entry[i];
                }
                handler.handleSCTPMessage(response, 0);
            }
        }
    }

    @Override
    public void close() {
        isOpen = false;
        sctpAssociationHandler.onShutdown();
    }

    @Override
    public void abortReceiver() {
        receiving = false;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    public interface IMockedRemote {
        void onMessage(byte[] data, Queue<Byte[]> queue);
    }
}
