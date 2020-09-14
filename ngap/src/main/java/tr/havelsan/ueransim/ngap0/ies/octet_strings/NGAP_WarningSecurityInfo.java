package tr.havelsan.ueransim.ngap0.ies.octet_strings;

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

public class NGAP_WarningSecurityInfo extends NGAP_OctetString {

    public NGAP_WarningSecurityInfo(OctetString value) {
        super(value);
    }

    public NGAP_WarningSecurityInfo(BitString value) {
        super(value);
    }

    public NGAP_WarningSecurityInfo(Octet[] octets) {
        super(octets);
    }

    public NGAP_WarningSecurityInfo(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_WarningSecurityInfo(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_WarningSecurityInfo(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "WarningSecurityInfo";
    }

    @Override
    public String getXmlTagName() {
        return "WarningSecurityInfo";
    }
}
