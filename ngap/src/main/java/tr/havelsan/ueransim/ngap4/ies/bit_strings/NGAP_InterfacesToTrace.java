package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_InterfacesToTrace extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "InterfacesToTrace";
    }

    @Override
    protected String getXmlTagName() {
        return "InterfacesToTrace";
    }
}
