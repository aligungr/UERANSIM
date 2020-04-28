package tr.havelsan.ueransim;

import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;

public class OutgoingMessage {
    public final NGAP_PDU ngapPdu;

    public OutgoingMessage(NGAP_PDU ngapPdu) {
        this.ngapPdu = ngapPdu;
    }
}
