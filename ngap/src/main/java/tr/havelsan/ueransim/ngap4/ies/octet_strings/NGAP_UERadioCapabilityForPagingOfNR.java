package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_UERadioCapabilityForPagingOfNR extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "UERadioCapabilityForPagingOfNR";
    }

    @Override
    protected String getXmlTagName() {
        return "UERadioCapabilityForPagingOfNR";
    }
}
