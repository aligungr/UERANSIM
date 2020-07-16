package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_LastVisitedUTRANCellInformation extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "LastVisitedUTRANCellInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "LastVisitedUTRANCellInformation";
    }
}
