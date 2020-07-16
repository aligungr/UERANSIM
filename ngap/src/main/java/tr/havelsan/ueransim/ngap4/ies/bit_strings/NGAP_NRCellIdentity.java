package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_NRCellIdentity extends NgapBitString {

    public NGAP_NRCellIdentity(BitString value) {
        super(value);
    }

    public NGAP_NRCellIdentity(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_NRCellIdentity(OctetString octetString) {
        super(octetString);
    }

    public NGAP_NRCellIdentity(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_NRCellIdentity(Octet[] octets) {
        super(octets);
    }

    public NGAP_NRCellIdentity(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_NRCellIdentity(byte[] octets) {
        super(octets);
    }

    public NGAP_NRCellIdentity(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_NRCellIdentity(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "NRCellIdentity";
    }

    @Override
    protected String getXmlTagName() {
        return "NRCellIdentity";
    }
}
