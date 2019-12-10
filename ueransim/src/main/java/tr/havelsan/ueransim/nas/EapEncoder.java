package tr.havelsan.ueransim.nas;

import tr.havelsan.ueransim.nas.eap.EEapType;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.exceptions.NotImplementedException;
import tr.havelsan.ueransim.utils.OctetOutputStream;

import java.util.LinkedHashMap;

public class EapEncoder {

    /**
     * Encodes EAP PDU into given stream
     */
    public static void eapPdu(OctetOutputStream stream, Eap pdu) {
        stream.writeOctet(pdu.code.intValue());
        stream.writeOctet(pdu.id);
        stream.writeOctet2(pdu.length);
        stream.writeOctet(pdu.EAPType.intValue());

        if (pdu.EAPType.equals(EEapType.EAP_AKA_PRIME)) {
            encodeAKAPrime(stream, (EapAkaPrime) pdu);
        } else if (pdu.EAPType.equals(EEapType.NOTIFICATION)) {
            encodeNotification(stream);
        } else {
            throw new NotImplementedException("eap type not implemented yet: " + pdu.EAPType.name());
        }
    }

    private static void encodeNotification(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }

    private static void encodeAKAPrime(OctetOutputStream stream, EapAkaPrime akaPrime) {
        stream.writeOctet(akaPrime.subType.intValue());
        stream.writeOctet2(0); // reserved 2-octet

        int c = 0;

        if (akaPrime.attributes == null) akaPrime.attributes = new LinkedHashMap<>();
        for (var entry : akaPrime.attributes.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            stream.writeOctet(key.intValue());
            stream.writeOctet((value.length + 2) / 4);
            stream.writeOctets(value.data);

            c++;
        }
    }
}
