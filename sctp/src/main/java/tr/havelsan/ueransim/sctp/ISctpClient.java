/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
