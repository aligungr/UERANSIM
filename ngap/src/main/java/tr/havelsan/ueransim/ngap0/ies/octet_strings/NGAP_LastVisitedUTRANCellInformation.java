package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_LastVisitedUTRANCellInformation extends NGAP_OctetString {

    public NGAP_LastVisitedUTRANCellInformation(OctetString value) {
        super(value);
    }

    public NGAP_LastVisitedUTRANCellInformation(BitString value) {
        super(value);
    }

    public NGAP_LastVisitedUTRANCellInformation(Octet[] octets) {
        super(octets);
    }

    public NGAP_LastVisitedUTRANCellInformation(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_LastVisitedUTRANCellInformation(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_LastVisitedUTRANCellInformation(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "LastVisitedUTRANCellInformation";
    }

    @Override
    public String getXmlTagName() {
        return "LastVisitedUTRANCellInformation";
    }
}
