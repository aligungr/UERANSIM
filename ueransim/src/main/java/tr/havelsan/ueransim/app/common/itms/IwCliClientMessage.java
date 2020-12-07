/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

import java.net.Socket;

public class IwCliClientMessage {
    public final Socket socket;
    public final byte[] data;

    public IwCliClientMessage(Socket socket, byte[] data) {
        this.socket = socket;
        this.data = data;
    }
}
