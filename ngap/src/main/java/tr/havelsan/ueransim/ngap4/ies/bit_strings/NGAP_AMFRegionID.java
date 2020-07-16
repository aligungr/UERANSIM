package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_AMFRegionID extends NgapBitString {

    public NGAP_AMFRegionID(BitString value) {
        super(value);
    }

    public NGAP_AMFRegionID(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_AMFRegionID(OctetString octetString) {
        super(octetString);
    }

    public NGAP_AMFRegionID(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_AMFRegionID(Octet[] octets) {
        super(octets);
    }

    public NGAP_AMFRegionID(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_AMFRegionID(byte[] octets) {
        super(octets);
    }

    public NGAP_AMFRegionID(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_AMFRegionID(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "AMFRegionID";
    }

    @Override
    protected String getXmlTagName() {
        return "AMFRegionID";
    }
}
