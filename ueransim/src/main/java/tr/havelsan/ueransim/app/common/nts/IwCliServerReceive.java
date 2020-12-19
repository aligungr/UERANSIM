/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import java.net.Socket;

public class IwCliServerReceive {
    public final Socket socket;
    public final byte[] data;

    public IwCliServerReceive(Socket socket, byte[] data) {
        this.socket = socket;
        this.data = data;
    }
}
