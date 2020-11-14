/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_PDU;

public class IwNgapReceive {
    public final Guami associatedAmf;
    public final int stream;
    public final NGAP_PDU ngapPdu;

    public IwNgapReceive(Guami associatedAmf, int stream, NGAP_PDU ngapPdu) {
        this.associatedAmf = associatedAmf;
        this.stream = stream;
        this.ngapPdu = ngapPdu;
    }
}
