package tr.havelsan.ueransim.nas;

import tr.havelsan.ueransim.core.exceptions.NotImplementedException;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.eap.EapAttributes;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet2;

public class EapEncoder {

    /**
     * Encodes EAP PDU into given stream
     */
    public static void eapPdu(OctetOutputStream stream, Eap pdu) {
        var innerStream = new OctetOutputStream();

        innerStream.writeOctet(pdu.code.intValue());
        innerStream.writeOctet(pdu.id);
        innerStream.writeOctet2(0); // dummy length
        innerStream.writeOctet(pdu.EAPType.intValue());

        if (pdu.EAPType.equals(Eap.EEapType.EAP_AKA_PRIME)) {
            encodeAKAPrime(innerStream, (EapAkaPrime) pdu);
        } else if (pdu.EAPType.equals(Eap.EEapType.NOTIFICATION)) {
            encodeNotification(innerStream);
        } else if (pdu.EAPType.equals(Eap.EEapType.IDENTITY)) {
            encodeIdentity(innerStream);
        } else {
            throw new NotImplementedException("eap type not implemented yet: " + pdu.EAPType.name());
        }

        var octets = innerStream.toOctetArray();
        var realLength = new Octet2(octets.length).toOctetArray();
        octets[2] = realLength[0];
        octets[3] = realLength[1];

        stream.writeOctets(octets);
    }

    private static void encodeNotification(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }

    private static void encodeIdentity(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }

    private static void encodeAKAPrime(OctetOutputStream stream, EapAkaPrime akaPrime) {
        stream.writeOctet(akaPrime.subType.intValue());
        stream.writeOctet2(0); // reserved 2-octet

        int c = 0;

        if (akaPrime.attributes == null) akaPrime.attributes = new EapAttributes();
        for (var entry : akaPrime.attributes.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            stream.writeOctet(key.intValue());
            stream.writeOctet((value.length + 2) / 4);
            stream.writeOctets(value.getAsArray());

            c++;
        }
    }
}
