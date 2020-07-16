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

public class NGAP_NGAP_Message extends NgapOctetString {

    public NGAP_NGAP_Message(OctetString value) {
        super(value);
    }

    public NGAP_NGAP_Message(BitString value) {
        super(value);
    }

    public NGAP_NGAP_Message(Octet[] octets) {
        super(octets);
    }

    public NGAP_NGAP_Message(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_NGAP_Message(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_NGAP_Message(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "NGAP-Message";
    }

    @Override
    protected String getXmlTagName() {
        return "NGAP-Message";
    }
}
