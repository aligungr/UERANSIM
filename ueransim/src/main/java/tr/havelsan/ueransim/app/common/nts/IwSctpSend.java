/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import java.util.UUID;

public class IwSctpSend {
    public final int streamNumber;
    public final byte[] data;
    public final UUID clientId;

    public IwSctpSend(int streamNumber, byte[] data, UUID clientId) {
        this.streamNumber = streamNumber;
        this.data = data;
        this.clientId = clientId;
    }
}
