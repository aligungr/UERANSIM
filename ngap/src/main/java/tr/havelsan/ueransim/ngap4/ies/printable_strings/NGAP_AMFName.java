package tr.havelsan.ueransim.ngap4.ies.printable_strings;

import tr.havelsan.ueransim.ngap4.core.NgapPrintableString;

public class NGAP_AMFName extends NgapPrintableString {

    @Override
    protected String getAsnName() {
        return "AMFName";
    }

    @Override
    protected String getXmlTagName() {
        return "AMFName";
    }
}
