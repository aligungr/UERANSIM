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

public class NGAP_WarningMessageContents extends NgapOctetString {

    public NGAP_WarningMessageContents(OctetString value) {
        super(value);
    }

    public NGAP_WarningMessageContents(BitString value) {
        super(value);
    }

    public NGAP_WarningMessageContents(Octet[] octets) {
        super(octets);
    }

    public NGAP_WarningMessageContents(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_WarningMessageContents(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_WarningMessageContents(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "WarningMessageContents";
    }

    @Override
    protected String getXmlTagName() {
        return "WarningMessageContents";
    }
}
