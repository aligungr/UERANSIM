package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_EUTRAencryptionAlgorithms extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "EUTRAencryptionAlgorithms";
    }

    @Override
    protected String getXmlTagName() {
        return "EUTRAencryptionAlgorithms";
    }
}
