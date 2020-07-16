package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_SecurityKey extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "SecurityKey";
    }

    @Override
    protected String getXmlTagName() {
        return "SecurityKey";
    }
}
