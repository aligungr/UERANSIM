package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_RATRestrictionInformation extends NgapBitString {

    public NGAP_RATRestrictionInformation(BitString value) {
        super(value);
    }

    public NGAP_RATRestrictionInformation(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_RATRestrictionInformation(OctetString octetString) {
        super(octetString);
    }

    public NGAP_RATRestrictionInformation(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_RATRestrictionInformation(Octet[] octets) {
        super(octets);
    }

    public NGAP_RATRestrictionInformation(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_RATRestrictionInformation(byte[] octets) {
        super(octets);
    }

    public NGAP_RATRestrictionInformation(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_RATRestrictionInformation(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "RATRestrictionInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "RATRestrictionInformation";
    }
}
