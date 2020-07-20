package tr.havelsan.ueransim.ngap0.ies.printable_strings;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_AMFName extends NGAP_PrintableString {

    public NGAP_AMFName(String value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AMFName";
    }

    @Override
    public String getXmlTagName() {
        return "AMFName";
    }
}
