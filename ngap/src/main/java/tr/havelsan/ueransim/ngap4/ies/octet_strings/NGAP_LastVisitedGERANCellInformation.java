package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_LastVisitedGERANCellInformation extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "LastVisitedGERANCellInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "LastVisitedGERANCellInformation";
    }
}
