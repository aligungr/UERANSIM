package tr.havelsan.ueransim;

import fr.marben.asnsdk.japi.spe.ExtSequenceValue;
import fr.marben.asnsdk.japi.spe.SequenceValue;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;

public class IncomingMessage {
    public final NGAP_PDU ngapPdu;
    public final SequenceValue ngapMessage;
    public final NasMessage nasMessage;

    public IncomingMessage(NGAP_PDU ngapPdu, SequenceValue ngapMessage, NasMessage nasMessage) {
        this.ngapPdu = ngapPdu;
        this.ngapMessage = ngapMessage;
        this.nasMessage = nasMessage;
    }

    public <T extends NasMessage> T getNasMessage(Class<T> messageType) {
        if (nasMessage == null) {
            return null;
        }
        if (messageType.isAssignableFrom(nasMessage.getClass())) {
            return (T) nasMessage;
        }
        return null;
    }

    public <T extends ExtSequenceValue> T getNgapMessage(Class<T> messageType) {
        if (ngapMessage == null) {
            return null;
        }
        if (messageType.isAssignableFrom(ngapMessage.getClass())) {
            return (T) ngapMessage;
        }
        return null;
    }
}
