package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_TAIListForInactiveItem extends NGAP_Sequence {

    public NGAP_TAI tAI;

    @Override
    public String getAsnName() {
        return "TAIListForInactiveItem";
    }

    @Override
    public String getXmlTagName() {
        return "TAIListForInactiveItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tAI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tAI"};
    }
}
