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

public class NGAP_LastVisitedEUTRANCellInformation extends NgapOctetString {

    public NGAP_LastVisitedEUTRANCellInformation(OctetString value) {
        super(value);
    }

    public NGAP_LastVisitedEUTRANCellInformation(BitString value) {
        super(value);
    }

    public NGAP_LastVisitedEUTRANCellInformation(Octet... octets) {
        super(octets);
    }

    public NGAP_LastVisitedEUTRANCellInformation(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_LastVisitedEUTRANCellInformation(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_LastVisitedEUTRANCellInformation(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "LastVisitedEUTRANCellInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "LastVisitedEUTRANCellInformation";
    }
}
