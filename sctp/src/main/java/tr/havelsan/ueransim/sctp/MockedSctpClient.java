/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
