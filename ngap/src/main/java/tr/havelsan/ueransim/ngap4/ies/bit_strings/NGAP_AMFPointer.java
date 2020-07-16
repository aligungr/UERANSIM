package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_AMFPointer extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "AMFPointer";
    }

    @Override
    protected String getXmlTagName() {
        return "AMFPointer";
    }
}
