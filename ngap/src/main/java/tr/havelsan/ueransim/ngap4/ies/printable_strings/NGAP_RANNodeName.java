package tr.havelsan.ueransim.ngap4.ies.printable_strings;

import tr.havelsan.ueransim.ngap4.core.NgapPrintableString;

public class NGAP_RANNodeName extends NgapPrintableString {

    @Override
    protected String getAsnName() {
        return "RANNodeName";
    }

    @Override
    protected String getXmlTagName() {
        return "RANNodeName";
    }
}
