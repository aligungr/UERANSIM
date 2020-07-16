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

public class NGAP_WarningType extends NgapOctetString {

    public NGAP_WarningType(OctetString value) {
        super(value);
    }

    public NGAP_WarningType(BitString value) {
        super(value);
    }

    public NGAP_WarningType(Octet... octets) {
        super(octets);
    }

    public NGAP_WarningType(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_WarningType(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_WarningType(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "WarningType";
    }

    @Override
    protected String getXmlTagName() {
        return "WarningType";
    }
}
