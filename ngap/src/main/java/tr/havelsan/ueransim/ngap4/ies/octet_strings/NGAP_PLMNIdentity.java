package tr.havelsan.ueransim.ngap4.ies.octet_strings;

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

public class NGAP_PLMNIdentity extends NgapOctetString {

    public NGAP_PLMNIdentity(OctetString value) {
        super(value);
    }

    public NGAP_PLMNIdentity(BitString value) {
        super(value);
    }

    public NGAP_PLMNIdentity(Octet... octets) {
        super(octets);
    }

    public NGAP_PLMNIdentity(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_PLMNIdentity(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_PLMNIdentity(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "PLMNIdentity";
    }

    @Override
    protected String getXmlTagName() {
        return "PLMNIdentity";
    }
}
