/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.sw;

public abstract class SocketWrapper {
    public final String type;

    public SocketWrapper() {
        this.type = this.getClass().getSimpleName();
    }
}
