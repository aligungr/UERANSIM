package tr.havelsan.ueransim.ngap0.ies.bit_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_AMFSetID extends NGAP_BitString {

    public NGAP_AMFSetID(BitString value) {
        super(value);
    }

    public NGAP_AMFSetID(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_AMFSetID(OctetString octetString) {
        super(octetString);
    }

    public NGAP_AMFSetID(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_AMFSetID(Octet[] octets) {
        super(octets);
    }

    public NGAP_AMFSetID(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_AMFSetID(byte[] octets) {
        super(octets);
    }

    public NGAP_AMFSetID(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_AMFSetID(String bits) {
        super(bits);
    }

    @Override
    public String getAsnName() {
        return "AMFSetID";
    }

    @Override
    public String getXmlTagName() {
        return "AMFSetID";
    }
}
