/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.sctp;

public interface ISctpClient {
    void start() throws Exception;

    void send(int streamNumber, byte[] data);

    void receiverLoop(ISctpHandler handler) throws Exception;

    void close();

    void abortReceiver();

    boolean isOpen();
}
