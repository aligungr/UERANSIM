/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

import tr.havelsan.ueransim.app.common.Guami;

public class IwNgapSend {
    public final int streamNumber;
    public final byte[] data;
    public final Guami associatedAmf;

    public IwNgapSend(int streamNumber, byte[] data, Guami associatedAmf) {
        this.streamNumber = streamNumber;
        this.data = data;
        this.associatedAmf = associatedAmf;
    }
}
