package com.runsim.backend.nas;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.eap.AkaPrime;
import com.runsim.backend.nas.eap.EAP;
import com.runsim.backend.nas.eap.EEapType;
import com.runsim.backend.utils.OctetOutputStream;

import java.util.LinkedHashMap;

public class EapEncoder {

    /**
     * Encodes EAP PDU into given stream
     */
    public static void eapPdu(OctetOutputStream stream, EAP pdu) {
        var innerStream = new OctetOutputStream();

        innerStream.writeOctet(pdu.code.value);
        innerStream.writeOctet(pdu.id);
        innerStream.writeOctet2(pdu.length);
        innerStream.writeOctet(pdu.EAPType.value);

        if (pdu.EAPType.equals(EEapType.EAP_AKA_PRIME)) {
            encodeAKAPrime(innerStream, (AkaPrime) pdu);
        } else if (pdu.EAPType.equals(EEapType.NOTIFICATION)) {
            encodeNotification(innerStream);
        } else {
            throw new NotImplementedException("eap type not implemented yet: " + pdu.EAPType.name);
        }

        stream.writeOctet2(innerStream.length());
        stream.writeStream(innerStream);
    }

    private static void encodeNotification(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }

    private static void encodeAKAPrime(OctetOutputStream stream, AkaPrime akaPrime) {
        stream.writeOctet(akaPrime.subType.value);
        stream.writeOctet2(0); // reserved 2-octet

        int c = 0;

        if (akaPrime.attributes == null) akaPrime.attributes = new LinkedHashMap<>();
        for (var entry : akaPrime.attributes.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            stream.writeOctet(key.value);
            stream.writeOctet((value.length + 2) / 4);
            stream.writeOctets(value.data);

            c++;
        }
    }
}
