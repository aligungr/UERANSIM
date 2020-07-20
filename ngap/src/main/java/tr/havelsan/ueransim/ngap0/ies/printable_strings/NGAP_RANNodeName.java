package tr.havelsan.ueransim.ngap0.ies.printable_strings;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_RANNodeName extends NGAP_PrintableString {

    public NGAP_RANNodeName(String value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RANNodeName";
    }

    @Override
    public String getXmlTagName() {
        return "RANNodeName";
    }
}
