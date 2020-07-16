package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_SerialNumber extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "SerialNumber";
    }

    @Override
    protected String getXmlTagName() {
        return "SerialNumber";
    }
}
