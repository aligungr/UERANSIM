package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_TAIListForInactiveItem extends NgapSequence {

    public NGAP_TAI tAI;

    @Override
    protected String getAsnName() {
        return "TAIListForInactiveItem";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIListForInactiveItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"tAI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"tAI"};
    }
}
