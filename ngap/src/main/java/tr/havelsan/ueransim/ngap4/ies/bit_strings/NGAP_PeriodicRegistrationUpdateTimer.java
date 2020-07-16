package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_PeriodicRegistrationUpdateTimer extends NgapBitString {

    public NGAP_PeriodicRegistrationUpdateTimer(BitString value) {
        super(value);
    }

    public NGAP_PeriodicRegistrationUpdateTimer(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_PeriodicRegistrationUpdateTimer(OctetString octetString) {
        super(octetString);
    }

    public NGAP_PeriodicRegistrationUpdateTimer(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_PeriodicRegistrationUpdateTimer(Octet[] octets) {
        super(octets);
    }

    public NGAP_PeriodicRegistrationUpdateTimer(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_PeriodicRegistrationUpdateTimer(byte[] octets) {
        super(octets);
    }

    public NGAP_PeriodicRegistrationUpdateTimer(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_PeriodicRegistrationUpdateTimer(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "PeriodicRegistrationUpdateTimer";
    }

    @Override
    protected String getXmlTagName() {
        return "PeriodicRegistrationUpdateTimer";
    }
}
