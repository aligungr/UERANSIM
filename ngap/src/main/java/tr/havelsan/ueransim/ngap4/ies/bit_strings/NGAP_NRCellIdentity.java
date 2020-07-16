package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_NRCellIdentity extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "NRCellIdentity";
    }

    @Override
    protected String getXmlTagName() {
        return "NRCellIdentity";
    }
}
