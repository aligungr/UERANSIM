package tr.havelsan.ueransim.events;

import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;

public class SctpReceiveEvent extends GnbEvent {
    public final NGAP_PDU ngapPdu;

    public SctpReceiveEvent(byte[] ngapPdu) {
        this(Ngap.perDecode(NGAP_PDU.class, ngapPdu));
    }

    public SctpReceiveEvent(NGAP_PDU ngapPdu) {
        this.ngapPdu = ngapPdu;
    }

    @Override
    public String toString() {
        return "SctpReceiveEvent{" +
                "ngapPdu=" + ngapPdu +
                '}';
    }
}
