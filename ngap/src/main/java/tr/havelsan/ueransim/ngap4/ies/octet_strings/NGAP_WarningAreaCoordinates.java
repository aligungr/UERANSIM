package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap4.pdu.*;
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

public class NGAP_WarningAreaCoordinates extends NGAP_OctetString {

    public NGAP_WarningAreaCoordinates(OctetString value) {
        super(value);
    }

    public NGAP_WarningAreaCoordinates(BitString value) {
        super(value);
    }

    public NGAP_WarningAreaCoordinates(Octet[] octets) {
        super(octets);
    }

    public NGAP_WarningAreaCoordinates(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_WarningAreaCoordinates(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_WarningAreaCoordinates(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "WarningAreaCoordinates";
    }

    @Override
    public String getXmlTagName() {
        return "WarningAreaCoordinates";
    }
}
