/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

import java.net.Socket;

public class IwCliSocketAccept {
    public final Socket socket;

    public IwCliSocketAccept(Socket socket) {
        this.socket = socket;
    }
}
