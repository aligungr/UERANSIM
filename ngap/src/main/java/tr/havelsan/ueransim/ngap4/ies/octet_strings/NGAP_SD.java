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

public class NGAP_SD extends NgapOctetString {

    public NGAP_SD(OctetString value) {
        super(value);
    }

    public NGAP_SD(BitString value) {
        super(value);
    }

    public NGAP_SD(Octet[] octets) {
        super(octets);
    }

    public NGAP_SD(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_SD(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_SD(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "SD";
    }

    @Override
    public String getXmlTagName() {
        return "SD";
    }
}
