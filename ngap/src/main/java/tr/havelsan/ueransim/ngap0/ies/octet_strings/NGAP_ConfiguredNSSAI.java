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

public class NGAP_ConfiguredNSSAI extends NGAP_OctetString {

    public NGAP_ConfiguredNSSAI(OctetString value) {
        super(value);
    }

    public NGAP_ConfiguredNSSAI(BitString value) {
        super(value);
    }

    public NGAP_ConfiguredNSSAI(Octet[] octets) {
        super(octets);
    }

    public NGAP_ConfiguredNSSAI(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_ConfiguredNSSAI(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_ConfiguredNSSAI(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "ConfiguredNSSAI";
    }

    @Override
    public String getXmlTagName() {
        return "ConfiguredNSSAI";
    }
}
