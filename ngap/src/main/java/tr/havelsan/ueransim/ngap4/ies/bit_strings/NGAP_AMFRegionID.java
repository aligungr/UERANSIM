package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_AMFRegionID extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "AMFRegionID";
    }

    @Override
    protected String getXmlTagName() {
        return "AMFRegionID";
    }
}
