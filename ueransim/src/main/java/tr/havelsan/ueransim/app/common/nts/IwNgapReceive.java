/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.ngap0.pdu.NGAP_PDU;

import java.util.UUID;

public class IwNgapReceive {
    public final UUID associatedAmf;
    public final int stream;
    public final NGAP_PDU ngapPdu;

    public IwNgapReceive(UUID associatedAmf, int stream, NGAP_PDU ngapPdu) {
        this.associatedAmf = associatedAmf;
        this.stream = stream;
        this.ngapPdu = ngapPdu;
    }
}
