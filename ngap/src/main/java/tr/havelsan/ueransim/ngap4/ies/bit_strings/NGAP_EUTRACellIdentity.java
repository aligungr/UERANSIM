package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_EUTRACellIdentity extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "EUTRACellIdentity";
    }

    @Override
    protected String getXmlTagName() {
        return "EUTRACellIdentity";
    }
}
