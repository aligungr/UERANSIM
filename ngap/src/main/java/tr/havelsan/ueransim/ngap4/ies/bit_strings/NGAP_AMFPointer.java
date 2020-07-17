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

public class NGAP_AMFPointer extends NgapBitString {

    public NGAP_AMFPointer(BitString value) {
        super(value);
    }

    public NGAP_AMFPointer(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_AMFPointer(OctetString octetString) {
        super(octetString);
    }

    public NGAP_AMFPointer(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_AMFPointer(Octet[] octets) {
        super(octets);
    }

    public NGAP_AMFPointer(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_AMFPointer(byte[] octets) {
        super(octets);
    }

    public NGAP_AMFPointer(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_AMFPointer(String bits) {
        super(bits);
    }

    @Override
    public String getAsnName() {
        return "AMFPointer";
    }

    @Override
    public String getXmlTagName() {
        return "AMFPointer";
    }
}
