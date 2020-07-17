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

public class NGAP_GTP_TEID extends NGAP_OctetString {

    public NGAP_GTP_TEID(OctetString value) {
        super(value);
    }

    public NGAP_GTP_TEID(BitString value) {
        super(value);
    }

    public NGAP_GTP_TEID(Octet[] octets) {
        super(octets);
    }

    public NGAP_GTP_TEID(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_GTP_TEID(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_GTP_TEID(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "GTP-TEID";
    }

    @Override
    public String getXmlTagName() {
        return "GTP-TEID";
    }
}
