package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_DataCodingScheme extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "DataCodingScheme";
    }

    @Override
    protected String getXmlTagName() {
        return "DataCodingScheme";
    }
}
