/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

import java.util.UUID;

public class IwNgapSend {
    public final int streamNumber;
    public final byte[] data;
    public final UUID associatedAmf;

    public IwNgapSend(int streamNumber, byte[] data, UUID associatedAmf) {
        this.streamNumber = streamNumber;
        this.data = data;
        this.associatedAmf = associatedAmf;
    }
}
