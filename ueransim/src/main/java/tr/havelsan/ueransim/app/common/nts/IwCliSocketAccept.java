/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.nts;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IwCliSocketAccept {
    public final Socket socket;
    public final InputStream inputStream;
    public final OutputStream outputStream;

    public IwCliSocketAccept(Socket socket, InputStream inputStream, OutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }
}
