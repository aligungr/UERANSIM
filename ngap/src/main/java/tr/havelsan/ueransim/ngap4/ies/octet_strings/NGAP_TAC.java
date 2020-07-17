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

public class NGAP_TAC extends NgapOctetString {

    public NGAP_TAC(OctetString value) {
        super(value);
    }

    public NGAP_TAC(BitString value) {
        super(value);
    }

    public NGAP_TAC(Octet[] octets) {
        super(octets);
    }

    public NGAP_TAC(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_TAC(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_TAC(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "TAC";
    }

    @Override
    public String getXmlTagName() {
        return "TAC";
    }
}
