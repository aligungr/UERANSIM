package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_InterfacesToTrace extends NgapBitString {

    public NGAP_InterfacesToTrace(BitString value) {
        super(value);
    }

    public NGAP_InterfacesToTrace(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_InterfacesToTrace(OctetString octetString) {
        super(octetString);
    }

    public NGAP_InterfacesToTrace(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_InterfacesToTrace(Octet[] octets) {
        super(octets);
    }

    public NGAP_InterfacesToTrace(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_InterfacesToTrace(byte[] octets) {
        super(octets);
    }

    public NGAP_InterfacesToTrace(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_InterfacesToTrace(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "InterfacesToTrace";
    }

    @Override
    protected String getXmlTagName() {
        return "InterfacesToTrace";
    }
}
