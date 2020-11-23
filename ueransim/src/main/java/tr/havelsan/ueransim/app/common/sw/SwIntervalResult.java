/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.sw;

public class SwIntervalResult extends SocketWrapper {
    public final String id;
    public final boolean isSuccess;
    public final String nodeName;
    public final long deltaMs;

    public SwIntervalResult(String id, boolean isSuccess, String nodeName, long deltaMs) {
        this.id = id;
        this.isSuccess = isSuccess;
        this.nodeName = nodeName;
        this.deltaMs = deltaMs;
    }
}
