package tr.havelsan.ueransim;

import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;

public class OutgoingMessage {
    public final NGAP_PDU ngapPdu;
    public final NasMessage plainNas;
    public final NasMessage securedNas;

    public OutgoingMessage(NGAP_PDU ngapPdu, NasMessage plainNas, NasMessage securedNas) {
        this.ngapPdu = ngapPdu;
        this.plainNas = plainNas;
        this.securedNas = securedNas;
    }
}
