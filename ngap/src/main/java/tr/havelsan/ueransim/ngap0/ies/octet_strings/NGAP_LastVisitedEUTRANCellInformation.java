package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_LastVisitedEUTRANCellInformation extends NGAP_OctetString {

    public NGAP_LastVisitedEUTRANCellInformation(OctetString value) {
        super(value);
    }

    public NGAP_LastVisitedEUTRANCellInformation(BitString value) {
        super(value);
    }

    public NGAP_LastVisitedEUTRANCellInformation(Octet[] octets) {
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
    public String getAsnName() {
        return "LastVisitedEUTRANCellInformation";
    }

    @Override
    public String getXmlTagName() {
        return "LastVisitedEUTRANCellInformation";
    }
}
