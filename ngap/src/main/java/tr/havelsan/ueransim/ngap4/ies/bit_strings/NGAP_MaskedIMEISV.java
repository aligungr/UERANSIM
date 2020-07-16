package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_MaskedIMEISV extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "MaskedIMEISV";
    }

    @Override
    protected String getXmlTagName() {
        return "MaskedIMEISV";
    }
}
