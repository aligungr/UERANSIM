package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_MessageIdentifier extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "MessageIdentifier";
    }

    @Override
    protected String getXmlTagName() {
        return "MessageIdentifier";
    }
}
