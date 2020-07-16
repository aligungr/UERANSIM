package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

public class NGAP_EUTRAencryptionAlgorithms extends NgapBitString {

    public NGAP_EUTRAencryptionAlgorithms(BitString value) {
        super(value);
    }

    public NGAP_EUTRAencryptionAlgorithms(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_EUTRAencryptionAlgorithms(OctetString octetString) {
        super(octetString);
    }

    public NGAP_EUTRAencryptionAlgorithms(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_EUTRAencryptionAlgorithms(Octet[] octets) {
        super(octets);
    }

    public NGAP_EUTRAencryptionAlgorithms(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_EUTRAencryptionAlgorithms(byte[] octets) {
        super(octets);
    }

    public NGAP_EUTRAencryptionAlgorithms(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_EUTRAencryptionAlgorithms(String bits) {
        super(bits);
    }

    @Override
    protected String getAsnName() {
        return "EUTRAencryptionAlgorithms";
    }

    @Override
    protected String getXmlTagName() {
        return "EUTRAencryptionAlgorithms";
    }
}
